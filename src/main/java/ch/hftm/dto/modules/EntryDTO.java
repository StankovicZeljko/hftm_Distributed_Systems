package ch.hftm.dto.modules;


import ch.hftm.dto.validation.ValidationEntryGroups;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.UniqueElements;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntryDTO {

    @Null(groups = ValidationEntryGroups.Post.class)
    private Long id;

    @NotNull(groups = ValidationEntryGroups.Post.class)
    @Size(min = 4, max = 100)
    private String title;

    @NotNull(groups = ValidationEntryGroups.Post.class)
    @Size(min = 5, max = 9999)
    private String content;


}
