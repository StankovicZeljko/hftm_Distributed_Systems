package ch.hftm.resource;

import ch.hftm.domain.Entry;
import ch.hftm.dto.EntryDTO;
import ch.hftm.repository.EntryRepository;
import ch.hftm.services.EntryMapper;

import javax.inject.Inject;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EntryResource {

    @Inject
    EntryRepository entryRepository;
    @Inject
    EntryMapper entryMapper;



    @GET
    public Response getAll() {

        List<EntryDTO> entryDTOList = entryRepository.findAll()
                                        .stream()
                                        .map(entry -> this.entryMapper.toDTO(entry))
                                        .collect(Collectors.toList());

        if(!entryDTOList.isEmpty()) {
            return Response.status(Response.Status.FOUND).entity(entryDTOList).build();

        } else {
            return Response.status(Response.Status.NOT_FOUND).entity(Json.createValue("Keine Einträge")).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        return this.entryRepository.findByIdOptional(id)
                .map(entry -> Response.ok(this.entryMapper.toDTO(entry)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).entity(Json.createValue("Kein solcher Eintrag")).build());
    }

    @POST
    @Transactional
    public Response create( EntryDTO entryDTO) {

        Entry entry = this.entryMapper.toEntry(entryDTO);

        this.entryRepository.persist(entry);

        if(this.entryRepository.isPersistent(entry)) {
            return Response.created(URI.create("/entries/" + entry.getId().toString())).entity(this.entryMapper.toDTO(entry)).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(Json.createValue("Body nicht korrekt")).build();

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
