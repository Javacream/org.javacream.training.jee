package org.javacream.store.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

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

}
