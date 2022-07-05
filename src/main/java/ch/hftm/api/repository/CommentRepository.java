package ch.hftm.api.repository;

import ch.hftm.api.domain.Comment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class CommentRepository implements PanacheRepository<Comment> {
}
