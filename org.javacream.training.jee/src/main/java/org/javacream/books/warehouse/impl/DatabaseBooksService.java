package org.javacream.books.warehouse.impl;

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
		book.setPrice(19.99);
		System.out.println(book);
		createdEventSender.fire(new BookEvent(isbn));
		return isbn;
	}//Hier erfolgt das eigentliche Speichern des book(!)

	public Book findBookByIsbn(String isbn) throws BookException {
		System.out.println(isbn);
		Book result = entityManager.find(Book.class, isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		Book result2 = entityManager.createQuery("select b from Book as b where b.isbn = '" + isbn + "'", Book.class).getSingleResult();
		Book result3 = (Book) entityManager.createNativeQuery("select * from BOOKS where isbn = '" + isbn + "'", Book.class).getSingleResult();
		List<Book> resultList = entityManager.createQuery("select b from Book as b", Book.class).getResultList();
		Book result4 = resultList.get(0);
		result.setTitle("CHANGED");
		System.out.println("1: " + result);
		System.out.println("2: " + result2);
		System.out.println("3: " + result3);
		System.out.println("4: " + result4);
		System.out.println("Identitities" + (result==result2) + ", " + (result==result3) + ", " + (result==result4));
		
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

	@Override
	@SuppressWarnings("unchecked")
	public Collection<String> findAllTitles() {
		Query nativeQuery = entityManager.createNativeQuery("SELECT BOOK_TITLE FROM BOOKS");
		List<String> result = nativeQuery.getResultList();
		Query query = entityManager.createQuery("SELECT b.title FROM Book as b");
		result = query.getResultList();
		return result;

	}
	@Override
	public List<Book> findBooksByTitle(String title){
		System.out.println("title=" + title);

		TypedQuery<Book> query = entityManager.createQuery("select b from Book as b where b.title like :title", Book.class);
		query.setParameter("title", title);
		return query.getResultList();
	}
	@Override
	public List<Book> findBooksByPriceRange(double min, double max){
		TypedQuery<Book> query = entityManager.createQuery("select b from Book as b where b.price < :max and b.price > :min", Book.class);
		query.setParameter("min", min);
		query.setParameter("max", max);
		return query.getResultList();
	}

	
}
