package org.javacream.books;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

@ApplicationScoped
@Path("/books")
public class BooksApplication {


	@Inject private BooksService booksService;
	

	@GET
	public String doBooksExample() {
		try {
			String isbn = booksService.newBook("JEE");
			System.out.println("Generated Book: " + isbn);
			Book book = booksService.findBookByIsbn(isbn);
			System.out.println("Found Book: " + book);
			System.out.println("Found Test-Book: " + booksService.findBookByIsbn("ISBN1"));
			
				
		} catch (BookException e) {
			e.printStackTrace();
		}
		return "OK";
	}
}
