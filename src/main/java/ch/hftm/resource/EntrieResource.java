package ch.hftm.resource;

import ch.hftm.module.Entrie;
import ch.hftm.repository.EntrieRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/entries")
@Produces(MediaType.APPLICATION_JSON)
public class EntrieResource {

    @Inject
    EntrieRepository entrieRepository;

    @GET
    public PanacheQuery<Entrie> entries() {
        return entrieRepository.findAll();
    }
}
