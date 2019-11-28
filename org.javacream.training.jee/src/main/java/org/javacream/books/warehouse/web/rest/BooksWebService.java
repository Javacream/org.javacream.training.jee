package org.javacream.books.warehouse.web.rest;

import java.util.Collection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

@ApplicationScoped
@Path("books")
public class BooksWebService {

	@Inject
	private BooksService booksService;

	public String newBook(String title) throws BookException {
		return booksService.newBook(title);
	}

	@GET
	@Path("{isbn}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Book findBookByIsbn(@PathParam("isbn") String isbn) throws BookException {
		return booksService.findBookByIsbn(isbn);
	}

	@Consumes(MediaType.APPLICATION_JSON)	
	public Book updateBook(Book bookValue) throws BookException {
		return booksService.updateBook(bookValue);
	}

	@DELETE
	@Path("{isbn}")
	public void deleteBookByIsbn(@PathParam("isbn") String isbn) throws BookException {
		
		booksService.deleteBookByIsbn(isbn);
	}

	public Collection<Book> findAllBooks() {
		return booksService.findAllBooks();
	}

}
