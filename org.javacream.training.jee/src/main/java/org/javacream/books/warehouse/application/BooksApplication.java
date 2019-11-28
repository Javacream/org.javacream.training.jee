package org.javacream.books.warehouse.application;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.javacream.books.warehouse.api.Book;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRES_NEW)
public class BooksApplication {
	@Inject private BooksDemo booksDemo;
	@PersistenceContext private EntityManager entityManager; 
	public void doTest() {
		Book book = new Book();
		book.setIsbn("ISBN1");
		book.setTitle("TITLE1");
		book.setPrice(19.99);
		entityManager.persist(book);
		book.setPrice(9.99);
		Book searched = entityManager.find(Book.class, "ISBN1");
		searched.setTitle("C H A N G E D");
		System.out.println(searched.getPrice());
		System.out.println(book.getTitle());
		System.out.println("Identity: " + (book == searched));
		
		Book searched2 = entityManager.createQuery("select b from Book as b", Book.class).getResultList().get(0);
		System.out.println("Identity: " + (book == searched2));
	
		entityManager.flush();//Absetzen der Statements
		entityManager.clear();//Alle Objekte sind ab jetzt detached
		book.setTitle("CHANGED AGAIN");
		Book attached = entityManager.merge(book);//book bleibt detached
		book.setPrice(6.66);
		attached.setPrice(66.66);
	}
	public void doTestPart2() {
		Book attached = entityManager.createQuery("select b from Book as b", Book.class).getResultList().get(0);
		booksDemo.demo();
		System.out.println(attached.getTitle());
		attached.setPrice(88.88);
		
	}

}
