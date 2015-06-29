package com.schremser.timetracker.api

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schremser.timetracker.api.utils.DateFormatter
import com.schremser.timetracker.mpi.model.Task
import com.schremser.timetracker.mpi.model.Track
import org.apache.log4j.Logger

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import java.lang.reflect.Array
import java.text.SimpleDateFormat;

/**
 * Created by bluemax on 21.06.15.
 */

@Path("date")
public class DateRequest {
    private static Logger log = Logger.getLogger(DateRequest.class);
    def static mapper = new ObjectMapper()
    static List<Track> tracks = new ArrayList<Track>();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{date}")

    public List<Track> getAllTracks(@PathParam("date") String date) {
        // retrieves all tracks for a day
        if (tracks.size() == 0) {
            File trackFile = new File("tracks", "${date}.json")
            if (trackFile.exists())
                tracks = mapper.readValue(trackFile, mapper.getTypeFactory().constructCollectionType(ArrayList.class, Track.class))
        }
        return tracks
    }

    @Path("{date}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)

    public Response addTrack(Track track) {
        if (track != null) {
            track.date = track.date ?: new SimpleDateFormat("yyyy-MM-dd").format(new Date())
            File trackFile = new File("tracks", "${track.date}.json");
            log.debug("Saving file: '" + trackFile.getAbsolutePath() + "'");
            try {
                boolean found = false
                tracks.each {_track ->
                    if (_track.toString().equals(track.toString())) {
                        found = true
                        _track.tasks.push(track.tasks.get(0))
                        return
                    }
                }
                if (!found) {
                    tracks.push(track)
                }
                mapper.writerWithDefaultPrettyPrinter().writeValue(trackFile, tracks)
            } catch (IOException ioe) {
            }
        }
        return Response.ok().build()
    }

}
