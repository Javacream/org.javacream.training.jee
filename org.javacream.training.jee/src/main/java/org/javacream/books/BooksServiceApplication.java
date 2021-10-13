package org.javacream.books;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

@Path("books")
@ApplicationScoped
public class BooksServiceApplication {

	private String isbn1;
	private String isbn2;

	@Inject
	private BooksService mapBooksService;

	@Path("init")
	@GET
	public String init() {
		try {
			isbn1 = mapBooksService.newBook("JEE");
			isbn2 = mapBooksService.newBook("Spring");
			return "OK";
		} catch (BookException e) {
			return e.getMessage();
		}

	}

	@Path("find")
	@GET
	public String find() {
		return mapBooksService.findAllBooks().toString();
	}

	@Path("clean")
	@GET
	public String clean() {
		try {
			mapBooksService.deleteBookByIsbn(isbn1);
			mapBooksService.deleteBookByIsbn(isbn2);
			return "OK";
		} catch (BookException e) {
			return e.getMessage();
		}
	}

}
