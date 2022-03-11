package org.javacream.books.warehouse.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.store.api.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MapBooksService implements BooksService {

	@Autowired
	@SequenceStrategy
	private IsbnGenerator isbnGenerator;
	@Autowired
	private Map<String, Book> books;
	@Autowired
	private StoreService storeService;

	{
		// books = new HashMap<String, Book>();
		System.out.println("###################################### constructing " + this);
	}

	@PostConstruct
	public void initMbs() {
		System.out.println("###################################### postconstructing " + this);

	}

	@PreDestroy
	public void destroyMbs() {
		System.out.println("###################################### predestroying " + this);

	}

	public String newBook(String title) throws BookException {
		String isbn = isbnGenerator.next();
		Book book = new Book();
		book.setIsbn(isbn);
		book.setTitle(title);
		books.put(isbn, book);
		return isbn;
	}

	public Book findBookByIsbn(String isbn) throws BookException {
		Book result = (Book) books.get(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_FOUND, isbn);
		}
		result.setAvailable(storeService.getStock("books", isbn) > 0);

		return result;
	}

	public Book updateBook(Book bookValue) throws BookException {
		books.put(bookValue.getIsbn(), bookValue);
		return bookValue;
	}

	public void deleteBookByIsbn(String isbn) throws BookException {
		Object result = books.remove(isbn);
		if (result == null) {
			throw new BookException(BookException.BookExceptionType.NOT_DELETED, isbn);
		}
	}

	public Collection<Book> findAllBooks() {
		return new ArrayList<Book>(books.values());
	}
}
