package org.javacream.books.warehouse.web;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

@Path("books")
public class BooksWebService {

	@Inject
	private BooksService booksService;

	@GET
	@Path("price")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> findBooksByPriceRange(@QueryParam("min")  double min, @QueryParam("max") double max) {
		return booksService.findBooksByPriceRange(min, max);
	}

	@GET
	@Path("title/{title}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Book> findBooksByTitle(@PathParam("title") String title) {
		return booksService.findBooksByTitle(title);
	}

	@GET
	@Path("titles")
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<String> findAllTitles() {
		return booksService.findAllTitles();
	}

	@POST
	@Path("{title}")
	public String newBook(@PathParam("title") String title) {
		try {
			return booksService.newBook(title);
		} catch (BookException e) {
			throw new NotAcceptableException("title");
		}
	}

	@GET
	@Path("{isbn}")
	@Produces(MediaType.APPLICATION_JSON)
	public Book findBookByIsbn(@PathParam("isbn") String isbn) throws BookException {
		try {
			return booksService.findBookByIsbn(isbn);
		} catch (BookException e) {
			throw new NotFoundException(isbn);
		}
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Book updateBook(Book book) {
		try {
			return booksService.updateBook(book);
		} catch (BookException e) {
			throw new NotAcceptableException(book.toString());
		}
	}

	@DELETE
	@Path("{isbn}")
	public void deleteBookByIsbn(@PathParam("isbn") String isbn) {
		try {
			booksService.deleteBookByIsbn(isbn);
		} catch (BookException e) {
			throw new NotFoundException(isbn);
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<Book> findAllBooks() {
		return booksService.findAllBooks();
	};

}
