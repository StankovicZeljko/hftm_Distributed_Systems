package ch.hftm.services;

import ch.hftm.domain.Entry;
import ch.hftm.dto.EntryDTO;
import ch.hftm.repository.EntryRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class MapperEntry {

    @Inject
    EntryRepository entryRepository;


    public EntryDTO toDTO(Entry entry) {

        EntryDTO entryDTO = new EntryDTO();
        entryDTO.setTitle(entryDTO.getTitle());
        entryDTO.setContent(entry.getContent());


        return entryDTO;
    }

    public Entry toEntry(EntryDTO entryDTO) {

        return new Entry(entryDTO.getTitle(), entryDTO.getContent());

    }

    public List<EntryDTO> getAllEntry() {

        return entryRepository.listAll()
                .stream()
                .map(entry -> toDTO(entry))
                .collect(Collectors.toList());

    }


}
