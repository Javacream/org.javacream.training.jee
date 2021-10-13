package org.javacream.books.warehouse.test;

import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;
import org.javacream.books.warehouse.impl.DatabaseBooksService;
import org.javacream.store.impl.SimpleStoreService;

public class BooksServiceTest {

	//@Test
	public void testBusinessObjects() {
		DatabaseBooksService mapBooksService = new DatabaseBooksService();
		CounterIsbnGenerator isbnGenerator = new CounterIsbnGenerator();
		isbnGenerator.setCountryCode("-de");
		mapBooksService.setIsbnGenerator(isbnGenerator);
		mapBooksService.setStoreService(new SimpleStoreService());
		isbnGenerator.setPrefix("TEST:");
		
		TestActor.doTest(mapBooksService);
		
	
	}

	

}
