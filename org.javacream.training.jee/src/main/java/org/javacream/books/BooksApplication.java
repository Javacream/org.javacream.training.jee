package org.javacream.books;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

@ApplicationScoped
@Path("/books")
public class BooksApplication {


	@Inject private BooksService booksService;
	@PersistenceContext private EntityManager entityManager;

	@GET
	@Transactional(TxType.REQUIRES_NEW)
	public String doBooksExample() {
		try {
			String isbn = booksService.newBook("JEE");
//			System.out.println("Generated Book: " + isbn);
			Book book = booksService.findBookByIsbn(isbn);
//			System.out.println("Found Book: " + book);
//			System.out.println("Found Test-Book: " + booksService.findBookByIsbn("ISBN1"));
//			System.out.println(booksService.findAllBooks());
			//booksService.deleteBookByIsbn(isbn);
			book.setPrice(59.99);
			Book book2 = entityManager.find(Book.class, isbn);
			book2.setPrice(19.99);
			System.out.println(book);
		} catch (BookException e) {
			e.printStackTrace();
		}
		return "OK";
	}
}
