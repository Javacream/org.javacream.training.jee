package org.javacream.books.warehouse.application;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.javacream.books.warehouse.api.Book;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class BooksDemo {

	@PersistenceContext private EntityManager entityManager;
	
	public void demo() {
		Book book = entityManager.createQuery("select b from Book as b", Book.class).getResultList().get(0);
		System.out.println(book.getPrice());
		book.setTitle("Changed one more time");
	}
}
