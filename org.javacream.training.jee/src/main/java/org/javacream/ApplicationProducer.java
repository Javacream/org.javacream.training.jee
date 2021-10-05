package org.javacream;

import java.util.HashMap;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.RandomStrategy;
import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;
import org.javacream.books.warehouse.api.Book;
import org.javacream.books.warehouse.api.BooksService;
import org.javacream.books.warehouse.impl.MapBooksService;
import org.javacream.store.api.StoreService;
import org.javacream.util.Config;

public class ApplicationProducer {

	@Produces @ApplicationScoped
	public BooksService booksService(StoreService storeService, @RandomStrategy IsbnGenerator isbnGenerator) {
		MapBooksService booksService =  new MapBooksService();
		booksService.setStoreService(storeService);
		booksService.setIsbnGenerator(isbnGenerator);
		HashMap<String, Book> books = new HashMap<String, Book>();
		//Demo Daten hinzufügen...
		booksService.setBooks(books);
		return booksService;
	}
	
	@Produces @Config(property="isbngenerator.prefix") String prefix() {
		return "ISBN:";
	}

	@Produces IsbnGenerator isbngenerator(RandomIsbnGenerator rg) {
		rg.setCountryCode("-de");
		return rg;
	}

}
