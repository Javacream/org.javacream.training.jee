package org.javacream.publishing.test;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.javacream.publishing.JpaBooksService;
import org.javacream.publishing.entities.Book;
import org.javacream.publishing.entities.BookStatistics;
import org.javacream.publishing.entities.Isbn;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class BookTests {

	private static EntityManager entityManager;

	@BeforeClass
	public static void init() {
		entityManager = Persistence.createEntityManagerFactory("JPA")
				.createEntityManager();
		bookController = new JpaBooksService();
		bookController.setEntityManager(entityManager);
	}

	private static JpaBooksService bookController;

	@Test
	public void doTest() {
		for (int i = 0; i < 20; i++) {
			bookController.createBook("a simple book", new Isbn(i, 2, 3, 4),
					200 + i, 9.95 * i, "book" + i, new BookStatistics(50 * i,
							new Date()));
		}
		Book book = bookController.findBookById(1l);
		Assert.assertNotNull(book);
		Book book2 = bookController.findBookById(2l);
		Assert.assertNotNull(book2);
		bookController.deleteBookById(2l);
		book2 = bookController.findBookById(2l);
		Assert.assertNull(book2);
		book.setDescription("changed description");
		bookController.update(book);
		try {
			bookController.createBook("a simple book", new Isbn(0, 2, 3, 4),
					200, 9.95, "book", new BookStatistics(50, new Date()));
			Assert.fail("Non unique isbn must be detected! ");
		} catch (RuntimeException e) {
			// OK
		}
		// TODO
		// try {
		// bookController.createBook("a simple book", new Isbn(99, 2, 3, 4),
		// -200, 9.95, "book", new BookInfo(50, new Date()));
		// Assert.fail("Negative pages must be detected! ");
		// } catch (RuntimeException e) {
		// // OK
		// }
		Isbn searchIsbn = new Isbn(9, 2, 3, 4);
		Book book3 = bookController.findBookByIsbn(searchIsbn);
		Assert.assertNotNull(book3);

		List<Book> books = bookController.findBooksByTitle("boo");
		Assert.assertEquals(19, books.size());
		books = bookController.findBooksByTitle("book2");
		Assert.assertEquals(1, books.size());
		books = bookController.findCheapBooks();
		Assert.assertEquals(10, books.size());
		books = bookController.findBestsellers();
		Assert.assertEquals(9, books.size());

		books = bookController.findBooksByTitleNative("boo");
		Assert.assertEquals(19, books.size());

		int bookCount = bookController.getBookCount();
		Assert.assertEquals(19, bookCount);
		int cheapBookCount = bookController.getCheapBooksCount();
		Assert.assertEquals(10, cheapBookCount);

		boolean deleted = bookController.deleteBookByIsbn(searchIsbn);
		Assert.assertTrue(deleted);

		List<Object> result = bookController.findBooksByTitleCriteria("boo");
		Assert.assertEquals(18, result.size());
		bookController.adaptPrice(.15);
		
	}

}
