package ch.hftm.api.resource;

import ch.hftm.api.domain.Entry;
import ch.hftm.api.dto.modules.EntryDTO;
import ch.hftm.api.dto.validation.ValidationEntryGroups;
import ch.hftm.api.repository.EntryRepository;
import ch.hftm.api.dto.mapper.EntryMapper;

import javax.inject.Inject;
import javax.json.Json;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
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
    public Response create( @Valid @ConvertGroup(to = ValidationEntryGroups.Post.class) EntryDTO entryDTO) {

        Entry entry = this.entryMapper.toEntry(entryDTO);

        if(this.entryRepository.entryTitelExistsNot(entry.getTitle())) {
            this.entryRepository.persist(entry);
        }

        if(this.entryRepository.isPersistent(entry)) {
            return Response.created(URI.create("/entries/" + entry.getId().toString())).entity(this.entryMapper.toDTO(entry)).build();
        }

        return Response.status(Response.Status.BAD_REQUEST).entity(Json.createValue("Titel Existiert bereits")).build();

    }



}