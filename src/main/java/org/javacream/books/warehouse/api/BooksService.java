package org.javacream.books.warehouse.api;

import java.util.Collection;


/**
 * 
 * @author Dr. Rainer Sawitzki
 * @company Javacream
 * @mailto rainer.sawitzki@javacream.org
 *
 */
public interface BooksService{
	String newBook(String title) throws BookException;

	Book findBookByIsbn(String isbn) throws BookException;
	
	Book updateBook(Book bookValue) throws BookException;
	
	void deleteBookByIsbn(String isbn) throws BookException;
	
	Collection<Book> findAllBooks();
}
