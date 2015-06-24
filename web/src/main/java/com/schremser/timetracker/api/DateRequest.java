package com.schremser.timetracker.api;

import com.schremser.timetracker.api.utils.DateFormatter;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;

/**
 * Created by bluemax on 21.06.15.
 */

@Path("date")
public class DateRequest {
    private static Logger log = Logger.getLogger(DateRequest.class);

    @Path("{date}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)

    public String getDatePlain(@PathParam("date") String date) {
        String parsedDate = DateFormatter.parseToString(date) + ".md";
        log.debug("Parsed '" + date + "' to '" + parsedDate + "'");

        File markdown = new File("tracks", parsedDate.replaceAll("-",File.separator));
        log.debug("Reading file: '" + markdown.getAbsolutePath() + "'");
        try {
            return FileUtils.readFileToString(markdown);
        } catch (IOException ioe) {
            return "File '" + markdown.getAbsolutePath() + "' does not exist.";
        }
    }

    @Path("{date}")
    @POST
    @Produces(MediaType.TEXT_PLAIN)

    public void postDate(@PathParam("date") String date, String content) {
        String parsedDate = DateFormatter.parseToString(date) + ".md";
        log.debug("Parsed '" + date + "' to '" + parsedDate + "'");

        File markdown = new File("tracks", parsedDate);
        log.debug("Saving file: '" + markdown.getAbsolutePath() + "'");
        try {
            FileUtils.touch(markdown);
            FileUtils.writeStringToFile(markdown, content);
        } catch (IOException ioe) {
        }
    }
}
