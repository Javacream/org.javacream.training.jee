package org.javacream.application;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.IsbnGeneratorQualifier;
import org.javacream.books.warehouse.application.BooksApplication;
import org.javacream.store.api.StoreService;

@ApplicationScoped
public class Application {
	@Inject
	private StoreService storeService;
	
	@Inject @IsbnGeneratorQualifier(IsbnGenerator.IsbnGeneratorStrategy.SEQUENCE)
	private IsbnGenerator isbnGenerator;
	@Inject @IsbnGeneratorQualifier(IsbnGenerator.IsbnGeneratorStrategy.RANDOM)
	private IsbnGenerator randomIsbnGenerator;
	
	@Inject 
	private BooksApplication booksApplication;
	
	public String run() {
		System.out.println(isbnGenerator.next());
		booksApplication.doTest();
		booksApplication.doTestPart2();
		return "StoreRequest: " + storeService.getStock("category", "item");
	}
}
