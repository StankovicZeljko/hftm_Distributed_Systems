package ch.hftm.repository;

import ch.hftm.domain.Entry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import java.util.List;


@RequestScoped
public class EntryRepository implements PanacheRepository<Entry> {



    public Response updateEntry(Long id, Entry entry) {

        // if param entry has both (titel, content) = 1
        // if param entry has only (titel) = 2
        // if param entry has only (content) = 3
        int changeType = 0;

        if(entry.getTitle() != null && entry.getContent() != null) {
            changeType = 1;
        } else if (entry.getTitle() != null && entry.getContent() == null) {
            changeType = 2;
        } else if (entry.getTitle() == null && entry.getContent() != null) {
            changeType = 3;
        }

        switch (changeType) {
            case 1:
                findById(id).setTitle(entry.getTitle());
                findById(id).setContent(entry.getContent());
                break;
            case 2:
                findById(id).setTitle(entry.getTitle());
                break;
            case 3:
                findById(id).setContent(entry.getContent());
                break;
        }

        return Response.status(Response.Status.CREATED).entity(findById(id)).build();

    }

    public Boolean entryTitelExistsNot( String title) {

        Boolean result = true;

        List<Entry> entries = findAll().list();

        for (Entry entry: entries) {
            if(entry.getTitle().equals(title)) {
                result = false;
            }
        }

        return result;
    }


}
