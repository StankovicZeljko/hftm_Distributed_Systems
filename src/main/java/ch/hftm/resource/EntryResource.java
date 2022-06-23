package ch.hftm.resource;

import ch.hftm.module.Entry;
import ch.hftm.repository.EntryRepository;

import javax.inject.Inject;
import javax.json.Json;
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
    public Response addEntries(Entry entry) {

        if(entry != null && entry.getTitle() != null) {

            if(this.entryRepository.entryTitelExistsNot(entry.getTitle())) {
                this.entryRepository.persist(entry);
                return Response.status(Response.Status.CREATED).entity(entry).build();

            } else {

                return Response.status(Response.Status.BAD_REQUEST).entity(Json.createValue("Titel schon vergeben")).build();
            }

        } else {

           return Response.status(Response.Status.BAD_REQUEST).entity(Json.createValue("Body nicht korrekt")).build();

        }

    }

    @PATCH
    @Transactional
    @Path("/{id}")
    public Response changeEntrie(@PathParam("id") Long id, Entry changedEntry) {

        Entry entry = this.entryRepository.findById(id);

        if(entry != null) {

            return this.entryRepository.updateEntry(id, changedEntry);

        } else {

            return Response.status(Response.Status.NOT_FOUND).entity(Json.createValue("ID existiert nicht")).build();
        }

    }


}
