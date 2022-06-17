package ch.hftm.repository;

import ch.hftm.module.Entry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;

@RequestScoped
public class EntryRepository implements PanacheRepository<Entry> {



}
