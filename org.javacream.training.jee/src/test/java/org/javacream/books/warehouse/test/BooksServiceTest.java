package org.javacream.books.warehouse.test;

import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.impl.SimpleStoreService;
import org.javacream.util.cdi.test.MockEventEmitter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BooksServiceTest {

	
	private MapBooksService booksService;
	private String isbn;

	@Before
	public void setUp() throws BookException {
		booksService = new MapBooksService();
		RandomIsbnGenerator randomIsbnGenerator = new RandomIsbnGenerator();
		randomIsbnGenerator.setCountryCode("-de");
		booksService.setIsbnGenerator(randomIsbnGenerator);
		booksService.setStoreService(new SimpleStoreService());
		booksService.setBookCreatedEventEmitter(new MockEventEmitter<>());
		booksService.setBookUpdatedEventEmitter(new MockEventEmitter<>());
		booksService.setBookDeletedEventEmitter(new MockEventEmitter<>());
		randomIsbnGenerator.setPrefix("TEST:");
		isbn = booksService.newBook("TEST");
	}
	
	@Test
	public void testSequence() {
		TestActor.doTest(booksService);
		
	
	}
	
	@Test public void testUpdate() throws BookException {
		final String NEW_TITLE="CHANGED";
		Book searchResult = booksService.findBookByIsbn(isbn);
		searchResult.setTitle(NEW_TITLE);
		Book searchResult2 = booksService.findBookByIsbn(isbn);
		Assert.assertEquals(NEW_TITLE, searchResult.getTitle());
		Assert.assertNotEquals(NEW_TITLE, searchResult2.getTitle());
		booksService.updateBook(searchResult);
		Book searchResult3 = booksService.findBookByIsbn(isbn);
		Assert.assertEquals(NEW_TITLE, searchResult3.getTitle());
		
	}

	

}
