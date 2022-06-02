package ch.hftm.resource;

import ch.hftm.module.Entrie;
import ch.hftm.repository.EntriesRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntriesResource {

    @Inject
    EntriesRepository entriesRepository;

    @GET
    public List<Entrie> entries() {

        return entriesRepository.listAll();
    }

    @POST
    @Transactional
    @Path("data")
    public Response addEntries(Entrie entrie) {

        entriesRepository.persist(entrie);

        return Response.status(Response.Status.CREATED).entity(entrie).build();

    }
}
