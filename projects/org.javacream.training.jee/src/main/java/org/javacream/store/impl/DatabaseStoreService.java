package org.javacream.store.impl;

import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.javacream.books.order.event.OrderEvent;
import org.javacream.store.api.StoreService;
import org.javacream.store.api.StoreService.DatabaseStrategy;
import org.javacream.util.aspect.Traced;
/*
 * create table STORE (category varchar(20), item varchar(20), stock integer, primary key (category, item))
 * insert into STORE (category, item, stock) values ('books', 'ISBN1', 42)
 */

@Traced
@DatabaseStrategy
public class DatabaseStoreService implements StoreService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public int getStock(String category, String item) {
		Query query = entityManager
				.createNativeQuery("select stock from STORE where category= :category and item=:item");
		query.setParameter("category", category);
		query.setParameter("item", item);
		try {
			Integer result = (Integer) query.getSingleResult();
			return result;
		} catch (RuntimeException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

	public void handleOrderEvent(@Observes OrderEvent event) {
		if (event.isOk()) {
			Query query = entityManager
					.createNativeQuery("select stock from STORE where category= :category and item=:item");
			query.setParameter("category", "books");
			query.setParameter("item", event.getIsbn());
			try {
				Integer stock = (Integer) query.getSingleResult();
				stock = stock - event.getNumber();
				Query updateQuery = entityManager
						.createNativeQuery("update STORE set stock = :stock where category= :category and item=:item");
				updateQuery.setParameter("stock", stock);
				updateQuery.setParameter("category", "books");
				updateQuery.setParameter("item", event.getIsbn());
				updateQuery.executeUpdate();
			} catch (RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
