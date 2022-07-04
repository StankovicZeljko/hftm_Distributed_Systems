package ch.hftm.repository;

import ch.hftm.domain.Entry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.core.Response;
import java.util.List;


@RequestScoped
public class EntryRepository implements PanacheRepository<Entry> {



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
