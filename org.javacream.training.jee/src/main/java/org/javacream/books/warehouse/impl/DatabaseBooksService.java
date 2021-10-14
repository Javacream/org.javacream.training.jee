package org.javacream.books.warehouse.impl;

import java.util.Collection;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BookException.BookExceptionType;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.javacream.util.aop.ProfilingInterceptor.Profiling;
import org.javacream.util.aop.TracingInterceptor.Tracing;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */
@ApplicationScoped
@Transactional
@Tracing
public class DatabaseBooksService implements BooksService {

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

	@Profiling
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
		return isbn;
	}// Hier erfolgt das eigentliche Speichern des book(!)

	
	@Override
	@Transactional(value=TxType.REQUIRES_NEW)
	public String newBookDemo(String title) throws BookException {
		String isbn = isbnGenerator.next();
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		try {
			entityManager.persist(book);
		} catch (RuntimeException e) {
			throw new BookException(BookExceptionType.NOT_CREATED, isbn);
		}
		throw new RuntimeException("TEST_ONLY");
		//return isbn;
	}// Hier erfolgt das eigentliche Speichern des book(!)

	
	public Book findBookByIsbn(String isbn) throws BookException {
		System.out.println(isbn);
		Book result = entityManager.find(Book.class, isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}

		result.setAvailable(storeService.getStock("books", isbn) > 0);

		return result;
	}

	public Book updateBook(Book book) throws BookException {
		entityManager.merge(book);
		return book;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		// TODO: Das muss besser gemacht werden! Ich lade doch zum Löschen nicht erst das ganze
		// Buch in den Hauptspeicher!
		// TODO: Korrektes Exception Handling
		Book result = entityManager.getReference(Book.class, isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}
		entityManager.remove(result);
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
	public List<Book> findBooksByTitle(String title) {
		System.out.println("title=" + title);

		TypedQuery<Book> query = entityManager.createQuery("select b from Book as b where b.title like :title",
				Book.class);
		query.setParameter("title", title);
		return query.getResultList();
	}

	@Override
	public List<Book> findBooksByPriceRange(double min, double max) {
		TypedQuery<Book> query = entityManager
				.createQuery("select b from Book as b where b.price < :max and b.price > :min", Book.class);
		query.setParameter("min", min);
		query.setParameter("max", max);
		return query.getResultList();
	}

}
