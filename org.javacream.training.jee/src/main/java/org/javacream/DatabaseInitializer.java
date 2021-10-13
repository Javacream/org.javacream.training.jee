package org.javacream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.javacream.books.warehouse.api.Book;

@ApplicationScoped
public class DatabaseInitializer {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public String doInit(@Observes @Initialized(ApplicationScoped.class) Object unused) {
		entityManager.createNativeQuery("drop table if exists ISBN").executeUpdate();
		entityManager.createNativeQuery("create table ISBN (isbn integer)").executeUpdate();
		entityManager.createNativeQuery("insert into ISBN values (0)").executeUpdate();
		
		for (int i = 0; i < 10; i++) {
			Book b = new Book();
			b.setIsbn("ISBN" + i);
			b.setTitle("Title" + i);
			b.setPrice(1.99 * i);
			entityManager.persist(b);
		}
		return "OK";
	}
}
