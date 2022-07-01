package ch.hftm.domain;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
public class Entry extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title, content;


    private Boolean allowComment;

    @OneToMany
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
