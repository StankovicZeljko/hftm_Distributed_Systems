package ch.hftm.resource;

import ch.hftm.module.Entry;
import ch.hftm.repository.EntryRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntryResource {

    @Inject
    EntryRepository entryRepository;

    @GET
    public List<Entry> entries() {

        return entryRepository.listAll();
    }

    @POST
    @Transactional
    @Path("create")
    public Response addEntries(Entry entry) {

        entryRepository.persist(entry);

        return Response.status(Response.Status.CREATED).entity(entry).build();

    }
}
