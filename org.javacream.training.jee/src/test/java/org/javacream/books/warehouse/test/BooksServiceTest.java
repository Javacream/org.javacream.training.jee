package org.javacream.books.warehouse.test;

import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.impl.SimpleStoreService;
import org.junit.Test;

public class BooksServiceTest {

	@Test
	public void testBusinessObjects() {
		MapBooksService mapBooksService = new MapBooksService();
		CounterIsbnGenerator isbnGenerator = new CounterIsbnGenerator();
		isbnGenerator.setCountryCode("-de");
		mapBooksService.setIsbnGenerator(isbnGenerator);
		mapBooksService.setStoreService(new SimpleStoreService());
		isbnGenerator.setPrefix("TEST:");
		
		TestActor.doTest(mapBooksService);
		
	
	}

	

}
