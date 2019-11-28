package org.javacream.books.warehouse.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.commons.lang3.SerializationUtils;
import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.IsbnGeneratorQualifier;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.javacream.util.aspect.Traced;
import org.javacream.util.qualifier.EventQualifier;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */
@ApplicationScoped
public class MapBooksService implements BooksService {

	public MapBooksService() {
		this.books = new HashMap<String, Book>();
	}

	public MapBooksService(IsbnGenerator isbngenerator, Map<String, Book> books, StoreService storeService) {
		super();
		this.isbnGenerator = isbngenerator;
		this.books = books;
		this.storeService = storeService;
	}

	@PostConstruct
	public void init() {
		for (int i = 0; i < 10; i++) {
			Book book = new Book();
			book.setIsbn("ISBN" + i);
			book.setTitle("TITLE" + i);
			books.put(book.getIsbn(), book);
		}
	}

	public void setBookCreatedEventEmitter(Event<BookEvent> bookCreatedEventEmitter) {
		this.bookCreatedEventEmitter = bookCreatedEventEmitter;
	}

	public void setBookDeletedEventEmitter(Event<BookEvent> bookDeletedEventEmitter) {
		this.bookDeletedEventEmitter = bookDeletedEventEmitter;
	}

	public void setBookUpdatedEventEmitter(Event<BookEvent> bookUpdatedEventEmitter) {
		this.bookUpdatedEventEmitter = bookUpdatedEventEmitter;
	}

	@Inject
	@EventQualifier(BookEventType.CREATED)
	private Event<BookEvent> bookCreatedEventEmitter;
	@Inject
	@EventQualifier(BookEventType.DELETED)
	private Event<BookEvent> bookDeletedEventEmitter;
	@Inject
	@EventQualifier(BookEventType.UPDATED)
	private Event<BookEvent> bookUpdatedEventEmitter;

	@Inject
	@IsbnGeneratorQualifier(IsbnGenerator.IsbnGeneratorStrategy.SEQUENCE)
	private IsbnGenerator isbnGenerator;

	private Map<String, Book> books;

	@Inject
	private StoreService storeService;

	{
		books = new HashMap<String, Book>();
	}

	public void setStoreService(StoreService storeService) {
		this.storeService = storeService;
	}

	public void setIsbnGenerator(IsbnGenerator isbnGenerator) {
		this.isbnGenerator = isbnGenerator;
	}

	@Traced
	public String newBook(String title) throws BookException {
		String isbn = isbnGenerator.next();
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		books.put(isbn, book);
		bookCreatedEventEmitter.fire(new BookEvent(isbn));
		return isbn;
	}

	public IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}

	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = (Book) books.get(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		result.setAvailable(storeService.getStock("books", isbn) > 0);

		return SerializationUtils.clone(result);
	}

	public Book updateBook(Book bookValue) throws BookException {
		books.put(bookValue.getIsbn(), SerializationUtils.clone(bookValue));
		bookUpdatedEventEmitter.fire(new BookEvent(bookValue.getIsbn()));
		return bookValue;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		Object result = books.remove(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}
		bookDeletedEventEmitter.fire(new BookEvent(isbn));
	}

	public Collection<Book> findAllBooks() {
		return SerializationUtils.clone(new ArrayList<Book>(books.values()));
	}

	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

}
