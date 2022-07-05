package ch.hftm.api.dto.modules;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EntryCommentDTO {

    private String title, content;
    private List<String> comments;
    private Boolean allow;


}
