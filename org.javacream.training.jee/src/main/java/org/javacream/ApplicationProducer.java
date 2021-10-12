package org.javacream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;

@ApplicationScoped
public class ApplicationProducer {
	
	@Produces @ApplicationScoped StoreService storeService() {
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		simpleStoreService.setStock(42);
		return simpleStoreService;
	}

	@Produces @ApplicationScoped @SequenceStrategy public IsbnGenerator isbnSequenceGenerator() {
		CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setPrefix("ISBN:");
		counterIsbnGenerator.setCountryCode("-is");
		counterIsbnGenerator.init();
		return counterIsbnGenerator;
	}
}
