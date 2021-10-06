package org.javacream.store.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.javacream.store.api.StoreService;

@ApplicationScoped
public class JpaStoreService implements StoreService {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public int getStock(String category, String item) {
		try {
			Query query = entityManager.createNativeQuery("select * from STORE where category=:cat and item=:item",
					StoreEntry.class);
			query.setParameter("cat", category);
			query.setParameter("item", item);
			StoreEntry storeEntry = (StoreEntry) query.getSingleResult();
			
			Query query2 = entityManager.createNativeQuery("select * from STORE",
					StoreEntry.class);
			StoreEntry storeEntry2 = (StoreEntry) query2.getResultList().get(0);
			System.out.println("Referenzvergleich: " + (storeEntry==storeEntry2));
			return storeEntry.getStock();
		} catch (NoResultException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}

}
