package ch.hftm.module;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Entity;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Entrie extends PanacheEntity {

    private String title, content;
}
