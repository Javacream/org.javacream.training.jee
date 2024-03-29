package org.javacream.books.warehouse.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */
@Transactional(value=TxType.REQUIRES_NEW, rollbackOn = {BookException.class})
public class JpaBooksService implements BooksService {

	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	@SequenceStrategy
	private IsbnGenerator isbnGenerator;
	@Inject
	private StoreService storeService;

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
		return isbn;
	}

	public IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	public Book findBookByIsbn(String isbn) throws BookException {
		try {
			Book result = entityManager.find(Book.class, isbn);
			result.setAvailable(storeService.getStock("Book", isbn) > 0);
			return result;
		} catch (RuntimeException e) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
	}

	public Book updateBook(Book book) throws BookException {
		try {
			entityManager.merge(book);
		} catch (RuntimeException e) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, book.getIsbn());
		}
		return book;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		try {
			entityManager.remove(entityManager.getReference(Book.class, isbn));
		} catch (RuntimeException e) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
	}

	public Collection<Book> findAllBooks() {
		return entityManager.createQuery("select b from Book as b", Book.class).getResultList();
	}

}
