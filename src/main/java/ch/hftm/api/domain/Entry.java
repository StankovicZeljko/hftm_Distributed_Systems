package ch.hftm.api.domain;


import ch.hftm.api.dto.validation.ValidationEntryGroups;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Getter
@Setter
public class Entry extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = ValidationEntryGroups.Post.class)
    private Long id;

    @NotNull(groups = ValidationEntryGroups.Post.class)
    @Size(min = 5, max = 100)
    private String title;

    @NotNull(groups = ValidationEntryGroups.Post.class)
    @Size(min = 4, max = 9999)
    private String content;


    @Null(groups = ValidationEntryGroups.Post.class)
    private Boolean allowComment;

    @OneToMany
    @Null(groups = ValidationEntryGroups.Post.class)
    @Null(groups = ValidationEntryGroups.Put.class)
    private List<Comment> commentList;

    public Entry () {
        this.allowComment = true;
    }

    public Entry(String title, String content) {
        this.title = title;
        this.content = content;
        this.allowComment = true;
    }

    public Entry(String title, String content, boolean allowComment) {
        this.title = title;
        this.content = content;
        this.allowComment = allowComment;
    }



}
