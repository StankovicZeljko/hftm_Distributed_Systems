package ch.hftm.repository;

import ch.hftm.domain.Comment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class CommentRepository implements PanacheRepository<Comment> {
}
