package org.javacream.books.warehouse.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import org.apache.commons.lang3.SerializationUtils;
import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGeneratorStrategy;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookChanged;
import org.javacream.books.warehouse.api.BookChanged.BookChangeType;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.javacream.util.aspect.Trace;

/**
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 * 
 */
@ApplicationScoped
public class MapBooksService implements BooksService {

	public MapBooksService(){
		this.books = new HashMap<String, Book>();
	}
	public MapBooksService(IsbnGenerator isbngenerator,
			Map<String, Book> books, StoreService storeService) {
		super();
		this.isbnGenerator = isbngenerator;
		this.books = books;
		this.storeService = storeService;
	}

	public void setBookCreatedEventEmitter(Event<BookChanged> bookCreatedEventEmitter) {
		this.bookCreatedEventEmitter = bookCreatedEventEmitter;
	}
	public void setBookDeletedEventEmitter(Event<BookChanged> bookDeletedEventEmitter) {
		this.bookDeletedEventEmitter = bookDeletedEventEmitter;
	}
	public void setBookUpdatedEventEmitter(Event<BookChanged> bookUpdatedEventEmitter) {
		this.bookUpdatedEventEmitter = bookUpdatedEventEmitter;
	}

	@Inject @BookChanged.Type(BookChangeType.CREATED)
	private Event<BookChanged> bookCreatedEventEmitter;
	@Inject @BookChanged.Type(BookChangeType.DELETED)
	private Event<BookChanged> bookDeletedEventEmitter;
	@Inject @BookChanged.Type(BookChangeType.UPDATED)
	private Event<BookChanged> bookUpdatedEventEmitter;


	@Inject @IsbnGeneratorStrategy(strategy="sequence")
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

	@Trace
	public String newBook(String title) throws BookException {
		String isbn = isbnGenerator.next();
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		books.put(isbn, book);
		bookCreatedEventEmitter.fire(new BookChanged(isbn));
		return isbn;
	}

	public IsbnGenerator getIsbnGenerator() {
		return isbnGenerator;
	}
	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = (Book) books.get(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND,
					isbn);
		}
		result.setAvailable(storeService.getStock("books", isbn) > 0);
		
		return SerializationUtils.clone(result);
	}

	public Book updateBook(Book bookValue) throws BookException {
		books.put(bookValue.getIsbn(), SerializationUtils.clone(bookValue));
		bookUpdatedEventEmitter.fire(new BookChanged(bookValue.getIsbn()));
		return bookValue;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		Object result = books.remove(isbn);
		if (result == null) {
			throw new BookException(
					BookException.BookExceptionType.NOT_DELETED, isbn);
		}
		bookDeletedEventEmitter.fire(new BookChanged(isbn));
	}


	public Collection<Book> findAllBooks() {
		return SerializationUtils.clone(new ArrayList<Book>(books.values()));
	}
	public void setBooks(Map<String, Book> books) {
		this.books = books;
	}

}





















