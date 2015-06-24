package com.schremser.timetracker;


import com.sun.jersey.api.container.ContainerFactory;
import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.*;
import com.sun.jersey.api.json.JSONConfiguration;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.grizzly.http.server.HttpHandler;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.StaticHttpHandler;

import javax.ws.rs.core.UriBuilder;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.logging.Logger;

/**
 * Created by bluemax on 23.06.15.
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) throws IOException, URISyntaxException {

        try {

            final ClassLoader loader = Main.class.getClassLoader();
            final URI uri = UriBuilder.fromUri("http://localhost").port(8081).build();

            log.info("Starting static HTTP Server");

            Enumeration<URL> enumeration  = loader.getResources("META-INF/resources/webjars/");
            ArrayList<URL> urls = new ArrayList<URL>();
            log.info("Scanning for resources in path: ");
            while (enumeration.hasMoreElements()) {
                URL url = enumeration.nextElement();
                log.info("\tresource " + url.getPath());
                urls.add(url);
            }

            StaticHttpHandler staticHttpHandler = new StaticHttpHandler(System.getProperty("user.dir") + "/src/main/resources/webapp");
            staticHttpHandler.addDocRoot("/");
            final HttpServer server = GrizzlyServerFactory.createHttpServer(uri, staticHttpHandler);

            CLStaticHttpHandler webjarHandler = new CLStaticHttpHandler(new URLClassLoader(urls.toArray(new URL[urls.size()])), "META-INF/resources/webjars/");
            server.getServerConfiguration().addHttpHandler(webjarHandler, "/webjars");

            log.info("registering Jersey via PackagesResourceConfig");
            ResourceConfig rc = new PackagesResourceConfig("com.schremser.timetracker.api");
            rc.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, true);
            final HttpHandler jerseyHandler = ContainerFactory.createContainer(
                    HttpHandler.class, rc);
            server.getServerConfiguration().addHttpHandler(jerseyHandler, "/api");

            server.getListener("grizzly").getFileCache().setEnabled(false);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    log.info("Deregistering Server");
                    server.shutdownNow();
                }
            }, "shutdownHook"));

            System.out.println("Press any key to stop the server...");
            Thread.currentThread().join();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
