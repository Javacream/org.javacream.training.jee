package org.javacream.books.warehouse.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.javacream.books.warehouse.api.Book;

@Transactional(Transactional.TxType.REQUIRES_NEW)
public class BookDemo {
	@PersistenceContext
	private EntityManager entityManager;

	public void doDemo(String isbn) {
		//Book result = entityManager.find(Book.class, isbn);
		Book result = entityManager.createQuery("select b from Book as b", Book.class).getResultList().get(0);
		result.setTitle("CHANGED");
	}

}
