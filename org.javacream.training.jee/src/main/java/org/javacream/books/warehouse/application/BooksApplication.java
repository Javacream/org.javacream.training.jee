package org.javacream.books.warehouse.application;


import java.util.Collection;

import javax.inject.Inject;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

public class BooksApplication {

	@Inject private BooksService booksService;
	public void doTest() {
		try {
			Collection<Book> books = booksService.findAllBooks();
			String j2eeTitle = "Spring";
			String isbn = booksService.newBook(j2eeTitle);
			books = booksService.findAllBooks();
			System.out.println(books);
			try {
				booksService.findBookByIsbn("ISBN-3");
			} catch (BookException e) {
				// OK
			}
			booksService.deleteBookByIsbn(isbn);
			try {
				booksService.deleteBookByIsbn(isbn);
			} catch (BookException e) {
				// OK
			}

		} catch (BookException bookException) {
			bookException.printStackTrace();
		}

	}

}
