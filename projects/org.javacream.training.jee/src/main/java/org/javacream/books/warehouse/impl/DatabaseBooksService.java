package org.javacream.books.warehouse.impl;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BookException.BookExceptionType;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.BooksService.DatabaseStrategy;
import org.javacream.books.warehouse.event.BookEvent;
import org.javacream.store.api.StoreService;
import org.javacream.util.CrudEventQualifiers;
import org.javacream.util.aspect.Monitored;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */

@DatabaseStrategy
@ApplicationScoped
@Monitored
@Transactional(Transactional.TxType.REQUIRED)
public class DatabaseBooksService implements BooksService {

	@Inject
	@CrudEventQualifiers.CREATED
	Event<BookEvent> createProducer;
	@Inject
	@CrudEventQualifiers.READ
	Event<BookEvent> readProducer;
	@Inject
	@CrudEventQualifiers.UPDATED
	Event<BookEvent> updatedProducer;
	@Inject
	@CrudEventQualifiers.DELETED
	Event<BookEvent> deletedProducer;
	@Inject
	BookDemo demo;
	@Inject
	@SequenceStrategy
	private IsbnGenerator isbnGenerator;
	@Inject
	@org.javacream.store.api.StoreService.DatabaseStrategy
	private StoreService storeService;
	@PersistenceContext
	private EntityManager entityManager;

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setIsbnGenerator(IsbnGenerator isbnGenerator) {
		this.isbnGenerator = isbnGenerator;
	}

	public String newBook(String title) throws BookException {
		String isbn = isbnGenerator.next();
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		entityManager.persist(book);
		createProducer.fire(new BookEvent(isbn));
		return isbn;
	}

	public IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = entityManager.find(Book.class, isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		result.setAvailable(storeService.getStock("books", isbn) > 0);
		demo.doDemo(isbn);
		System.out.println(result.getTitle());
		readProducer.fire(new BookEvent(isbn));
		return result;
	}

	public Book updateBook(Book bookValue) throws BookException {
		try {
			Book updated = entityManager.merge(bookValue);
			updatedProducer.fire(new BookEvent(updated.getIsbn()));
			return updated;
		} catch (RuntimeException e) {
			throw new BookException(BookExceptionType.CONSTRAINT, e.getMessage());
		}
	}

	public void _deleteBookByIsbn(String isbn) throws BookException {
		try {
			Book toDelete = entityManager.getReference(Book.class, isbn);
			entityManager.remove(toDelete);
		} catch (RuntimeException e) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		Query query = entityManager.createNativeQuery("delete from BOOKS where isbn = :isbn");
		query.setParameter("isbn", isbn);
		int affected = query.executeUpdate();
		if (affected == 0) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}else {
			deletedProducer.fire(new BookEvent(isbn));

		}
	}

	public Collection<Book> findAllBooks() {
		return entityManager.createQuery("select b from Book as b", Book.class).getResultList();
	}

}
