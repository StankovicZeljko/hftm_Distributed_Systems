package ch.hftm.repository;

import ch.hftm.module.Entrie;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EntriesRepository implements PanacheRepository<Entrie> {



}
