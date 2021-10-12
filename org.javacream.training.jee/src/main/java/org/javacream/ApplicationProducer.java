package org.javacream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;
import org.javacream.store.api.StoreService;
import org.javacream.store.impl.SimpleStoreService;
import org.javacream.util.qualifiers.Config;

@ApplicationScoped
public class ApplicationProducer {
	
	@Produces @ApplicationScoped StoreService storeService(@Config(property = "storeService.stock") String stockString) {
		SimpleStoreService simpleStoreService = new SimpleStoreService();
		simpleStoreService.setStock(Integer.parseInt(stockString));
		return simpleStoreService;
	}

	@Produces @ApplicationScoped @SequenceStrategy public IsbnGenerator isbnSequenceGenerator(CounterIsbnGenerator counterIsbnGenerator) {
		//CounterIsbnGenerator counterIsbnGenerator = new CounterIsbnGenerator();
		counterIsbnGenerator.setPrefix("ISBN:");
		counterIsbnGenerator.setCountryCode("-is");
		//counterIsbnGenerator.init();
		return counterIsbnGenerator;
	}
	
	@Produces @Config(property = "storeService.stock") public String stock() {
		return "4711";
	}
	@Produces @Config(property = "isbngenerator.prefix") public String isbnGeneratorPrefix() {
		return "Isbn:";
	}
	@Produces @Config(property = "isbngenerator.countryCode") public String isbnGeneratorCountryCode() {
		return "-dk";
	}
}
