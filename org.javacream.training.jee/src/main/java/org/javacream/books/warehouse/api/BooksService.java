package org.javacream.books.warehouse.api;

import java.util.Collection;
import java.util.List;


public interface BooksService{
	String newBook(String title) throws BookException;

	Book findBookByIsbn(String isbn) throws BookException;
	
	Book updateBook(Book bookValue) throws BookException;
	
	void deleteBookByIsbn(String isbn) throws BookException;
	
	Collection<Book> findAllBooks();

	List<Book> findBooksByPriceRange(double min, double max);

	List<Book> findBooksByTitle(String title);

	Collection<String> findAllTitles();
}
