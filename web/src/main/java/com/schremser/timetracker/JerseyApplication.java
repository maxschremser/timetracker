package com.schremser.timetracker;

import com.schremser.timetracker.api.DateRequest;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by bluemax on 21.06.15.
 */
public class JerseyApplication extends ResourceConfig {
    public JerseyApplication() {
        register(DateRequest.class);
        // register(JacksonFeature.class);
    }



}
