package org.javacream.store.impl;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.javacream.books.warehouse.api.BooksService.BookEvent;
import org.javacream.books.warehouse.api.BooksService.BookEventType;
import org.javacream.store.api.StoreService;
import org.javacream.util.qualifier.Business;
import org.javacream.util.qualifier.EventQualifier;

@ApplicationScoped
@Business
@Transactional(Transactional.TxType.REQUIRED)
public class DatabaseStoreService implements StoreService {
	@PersistenceContext
	private EntityManager entityManager;

	public int getStock(String category, String id) {
		Query query = entityManager
				.createNativeQuery("select stock from stock where category = ? and item = ?");
		query.setParameter(1, category);
		query.setParameter(2, id);
		try {
			Integer result = (Integer) query.getSingleResult();
			return result.intValue();
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public void handleBookCreated(@Observes @EventQualifier(BookEventType.CREATED) BookEvent bookChanged) {
		System.out.println("BOOK CREATED: " + bookChanged.getIsbn());
	}

	public void handleBookDeleted(@Observes @EventQualifier(BookEventType.DELETED) BookEvent bookChanged) {
		System.out.println("BOOK DELETED: " + bookChanged.getIsbn());
	}

}
