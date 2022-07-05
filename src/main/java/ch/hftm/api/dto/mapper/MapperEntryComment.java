package ch.hftm.api.dto.mapper;

import ch.hftm.api.domain.Entry;
import ch.hftm.api.dto.modules.EntryCommentDTO;
import ch.hftm.api.repository.EntryRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class MapperEntryComment {

    @Inject
    EntryRepository entryRepository;


    public EntryCommentDTO getById(Long id) {

        return convert(this.entryRepository.findById(id));
    }


    private EntryCommentDTO convert(Entry entry) {

        EntryCommentDTO entryCommentDTO = new EntryCommentDTO();
        entryCommentDTO.setTitle(entry.getTitle());
        entryCommentDTO.setContent(entry.getContent());
        entryCommentDTO.setAllow(entry.getAllowComment());

        List<String> commentsList = new ArrayList<>();

        if(entry.getCommentList().isEmpty()) {
            entryCommentDTO.setComments(commentsList);
        } else {
            entry.getCommentList()
                    .stream()
                    .forEach(comment -> {
                        commentsList.add(comment.getContent());
                    });

            entryCommentDTO.setComments(commentsList);
        }



        return entryCommentDTO;

    }


}
