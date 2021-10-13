package org.javacream.books.warehouse.impl;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.javacream.books.event.BookEvent;
import org.javacream.books.event.BookEvent.Created;
import org.javacream.books.event.BookEvent.Deleted;
import org.javacream.books.event.BookEvent.Updated;
import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BookException.BookExceptionType;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */
@ApplicationScoped
@Transactional
public class DatabaseBooksService implements BooksService {

	@Inject
	@Created
	Event<BookEvent> createdEventSender;
	@Inject
	@Updated
	Event<BookEvent> updatedEventSender;
	@Inject
	@Deleted
	Event<BookEvent> deletedEventSender;

	@Inject
	@SequenceStrategy
	IsbnGenerator isbnGenerator;
	@Inject
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
		try {
			entityManager.persist(book);
		} catch (RuntimeException e) {
			throw new BookException(BookExceptionType.NOT_CREATED, isbn);
		}
		createdEventSender.fire(new BookEvent(isbn));
		return isbn;
	}

	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = (Book) entityManager.find(Book.class, isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		result.setAvailable(storeService.getStock("books", isbn) > 0);

		return result;
	}

	public Book updateBook(Book book) throws BookException {
		entityManager.merge(book);
		updatedEventSender.fire(new BookEvent(book.getIsbn()));
		return book;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		// TODO: Das muss gemacht werden! Ich lade doch zum Löschen nicht erst das ganze
		// Buch in den Hauptspeicher!
		// TODO: Korrektes Exception Handling
		Book result = entityManager.find(Book.class, isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}
		entityManager.remove(result);
		deletedEventSender.fire(new BookEvent(isbn));
	}

	public Collection<Book> findAllBooks() {
//		Query query = entityManager.createNativeQuery("SELECT * FROM BOOKS");
//		List<Object[]> result = query.getResultList();
		Query nativeQuery = entityManager.createNativeQuery("SELECT * FROM BOOKS", Book.class);
		TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book as b", Book.class);
		@SuppressWarnings("unchecked")
		List<Book> result = nativeQuery.getResultList();
		result = query.getResultList();
		return result;

	}

	@SuppressWarnings("unchecked")
	public Collection<String> findAllTitles() {
		Query nativeQuery = entityManager.createNativeQuery("SELECT BOOK_TITLE FROM BOOKS");
		List<String> result = nativeQuery.getResultList();
		Query query = entityManager.createQuery("SELECT b.title FROM Book as b");
		result = query.getResultList();
		return result;

	}
	public List<Book> findBooksByTitle(String title){
		TypedQuery<Book> query = entityManager.createQuery("select b from Book as b where b.title like :title", Book.class);
		query.setParameter("title", title);
		return query.getResultList();
	}

	
}
