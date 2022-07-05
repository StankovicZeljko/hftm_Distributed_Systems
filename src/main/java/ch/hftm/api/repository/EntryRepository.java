package ch.hftm.api.repository;

import ch.hftm.api.domain.Entry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
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
