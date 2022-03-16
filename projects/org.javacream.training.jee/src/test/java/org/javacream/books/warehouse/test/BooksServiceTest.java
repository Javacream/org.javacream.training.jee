package org.javacream.books.warehouse.test;

import java.util.HashMap;

import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.impl.DatabaseStoreService;

public class BooksServiceTest {

	//@Test
	public void testBusinessObjects() {
		MapBooksService mapBooksService = new MapBooksService();
		mapBooksService.setBooks(new HashMap<String, Book>());
		RandomIsbnGenerator randomIsbnGenerator = new RandomIsbnGenerator();
		randomIsbnGenerator.init();
		randomIsbnGenerator.setCountryCode("-de");
		mapBooksService.setIsbnGenerator(randomIsbnGenerator);
		mapBooksService.setStoreService(new DatabaseStoreService());
		randomIsbnGenerator.setPrefix("TEST:");
		
		TestActor.doTest(mapBooksService);
		
	
	}

	

}
