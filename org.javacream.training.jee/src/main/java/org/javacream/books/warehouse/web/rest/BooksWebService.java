package org.javacream.books.warehouse.web.rest;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
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

@ApplicationScoped
@Path("books")
public class BooksWebService {

	@Inject
	private BooksService booksService;

	@POST
	@Path("{title}")
	public String newBook(@PathParam("title")String title) throws BookException {
		return booksService.newBook(title);
	}

	@GET
	@Path("{isbn}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public WebBookInfo findBookByIsbn(@PathParam("isbn") String isbn) throws BookException {
		return assemble(booksService.findBookByIsbn(isbn));
	}

	@PUT
	@Path("{isbn}/price")
	public void updateBookPrice(@PathParam("isbn")String isbn, @QueryParam("price")double price) throws BookException {
		Book book = booksService.findBookByIsbn(isbn);
		book.setPrice(price);
		booksService.updateBook(book);
	}
	
	@PUT
	@Path("{isbn}/title")
	public void updateBookTitle(@PathParam("isbn") String isbn, @QueryParam("title")String title) throws BookException {
		Book book = booksService.findBookByIsbn(isbn);
		book.setTitle(title);
		booksService.updateBook(book);
	}

	@DELETE
	@Path("{isbn}")
	public void deleteBookByIsbn(@PathParam("isbn") String isbn) throws BookException {
		
		booksService.deleteBookByIsbn(isbn);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Collection<WebBookInfo> findAllBooks() {
		return assemble(booksService.findAllBooks());
	}

	private Collection<WebBookInfo> assemble(Collection<Book> books){
		return books.stream().map(b -> assemble(b)).collect(Collectors.toList());
	}
	private WebBookInfo assemble(Book book) {
		return new WebBookInfo(book.getIsbn(), book.getTitle());
	}
}
