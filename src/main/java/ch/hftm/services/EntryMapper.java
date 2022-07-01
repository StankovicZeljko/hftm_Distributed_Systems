package ch.hftm.services;


import ch.hftm.domain.Entry;
import ch.hftm.dto.EntryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "cdi")
public interface EntryMapper {

    @Mapping(target = "id", source = "id")
    EntryDTO toDTO(Entry entry);

    @Mapping(target = "id", ignore = true)
    Entry toEntry(EntryDTO entryDTO);


}
