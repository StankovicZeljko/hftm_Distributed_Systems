package ch.hftm.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/blog")
public class BlogResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String blog() {
        return "Willkommen auf meinem Blog!";
    }

}
