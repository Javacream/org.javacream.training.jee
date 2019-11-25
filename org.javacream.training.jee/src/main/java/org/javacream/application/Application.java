package org.javacream.application;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.impl.IsbnGeneratorStrategy;
import org.javacream.books.warehouse.application.BooksApplication;
import org.javacream.store.api.StoreService;

@ApplicationScoped
public class Application {
	@Inject
	private StoreService storeService;
	
	@Inject @IsbnGeneratorStrategy(strategy="sequence")
	private IsbnGenerator isbnGenerator;
	@Inject @IsbnGeneratorStrategy(strategy="random")
	private IsbnGenerator randomIsbnGenerator;
	
	@Inject 
	private BooksApplication booksApplication;
	
	public String run() {
		System.out.println(isbnGenerator.next());
		booksApplication.doTest();
		return "StoreRequest: " + storeService.getStock("category", "item");
	}
}
