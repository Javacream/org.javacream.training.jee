package org.javacream.store.impl;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.javacream.books.warehouse.api.BookChanged;
import org.javacream.store.api.StoreService;
import org.javacream.util.inject.Business;

@ApplicationScoped
@Business
public class DatabaseStoreService implements StoreService {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional(Transactional.TxType.REQUIRED)
	public int getStock(String category, String id) {
		Query query = entityManager
				.createNativeQuery("select stock from stock where category = :category and item = :item");
		query.setParameter("category", category);
		query.setParameter("item", id);
		try {
			BigDecimal result = (BigDecimal) query.getSingleResult();
			return result.intValue();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}	
	public void handleBookCreated(@Observes @BookChanged.Type(BookChanged.BookChangeType.CREATED) BookChanged bookChanged) {
		System.out.println("BOOK CREATED: " + bookChanged.getIsbn());
	}
	public void handleBookDeleted(@Observes @BookChanged.Type(BookChanged.BookChangeType.DELETED) BookChanged bookChanged) {
		System.out.println("BOOK DELETED: " + bookChanged.getIsbn());
	}

}
