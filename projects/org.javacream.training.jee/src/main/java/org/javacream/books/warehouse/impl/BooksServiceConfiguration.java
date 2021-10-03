package org.javacream.books.warehouse.impl;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.inject.Produces;
import javax.inject.Named;

import org.javacream.books.warehouse.api.Book;
import org.javacream.util.Dev;
import org.javacream.util.Prod;

public class BooksServiceConfiguration {

	
	@Produces @Prod @Named("forBooksService") public Map<String, Book> booksMapForProd(){
		return new HashMap<String, Book>();
	}
	@Produces @Dev @Named("forBooksService") public Map<String, Book> booksMapForDev(){
		HashMap<String, Book> data = new HashMap<String, Book>();
		Book b = new Book();
		b.setIsbn("ISBN-TEST");
		b.setPrice(9.99);
		b.setTitle("TITLE");
		data.put(b.getIsbn(), b);
		return data;
	}
}
