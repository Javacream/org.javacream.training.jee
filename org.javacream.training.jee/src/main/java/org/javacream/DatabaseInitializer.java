package org.javacream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@ApplicationScoped
public class DatabaseInitializer {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public String doInit(@Observes @Initialized(ApplicationScoped.class) Object unused) {
		entityManager.createNativeQuery("drop table if exists ISBN").executeUpdate();
		entityManager.createNativeQuery("create table ISBN (isbn integer)").executeUpdate();
		entityManager.createNativeQuery("insert into ISBN values (0)").executeUpdate();
		return "OK";
	}
}
