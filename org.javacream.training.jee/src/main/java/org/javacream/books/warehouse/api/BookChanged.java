package org.javacream.books.warehouse.api;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

public class BookChanged {
	public BookChanged(String isbn) {
		super();
		this.isbn = isbn;
	}

	public String getIsbn() {
		return isbn;
	}

	private String isbn;

	@Target({ ElementType.TYPE, ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
	@Retention(RetentionPolicy.RUNTIME)
	@Qualifier
	public @interface Type {
		BookChangeType value();
	}
	
	public enum BookChangeType{
		CREATED, UPDATED, DELETED;
	}
}
