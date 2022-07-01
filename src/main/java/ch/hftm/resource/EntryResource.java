package ch.hftm.resource;

import ch.hftm.domain.Entry;
import ch.hftm.dto.EntryDTO;
import ch.hftm.repository.EntryRepository;
import ch.hftm.services.MapperEntry;
import io.quarkus.logging.Log;

import javax.inject.Inject;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntryResource {

    @Inject
    EntryRepository entryRepository;

    private MapperEntry mapperEntry;

    @GET
    public Response entries() {

        List<Entry> entryList = entryRepository.findAll().list();

        if(!entryList.isEmpty()) {
            return Response.status(Response.Status.FOUND).entity(mapperEntry.getAllEntry()).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(Json.createValue("Keine Einträge")).build();
        }
    }

    @POST
    @Transactional
    public Response addEntry( EntryDTO entryDTO) {

        System.out.println(entryDTO);

        Entry entry = mapperEntry.toEntry(entryDTO);

        System.out.println(entry.getContent());
        System.out.println(entry.getTitle());

        this.entryRepository.persist(entry);

        return Response.ok().build();

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
