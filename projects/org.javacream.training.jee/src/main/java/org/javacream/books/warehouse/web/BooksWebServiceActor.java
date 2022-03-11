package org.javacream.books.warehouse.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.api.BooksService.InMemoryStrategy;

@Path("books")
public class BooksWebServiceActor {

	@Inject @InMemoryStrategy private BooksService booksService;
	@GET @Produces (MediaType.TEXT_PLAIN) public String doSequence() throws BookException {
		String isbn = booksService.newBook("JEE");
		return booksService.findBookByIsbn(isbn).toString();
	}
}
