package org.javacream.books.isbngenerator.impl.decorators;

import jakarta.decorator.Decorator;
import jakarta.decorator.Delegate;
import jakarta.enterprise.inject.Any;
import jakarta.inject.Inject;

import org.javacream.books.isbngenerator.api.IsbnGenerator;

@Decorator
//@Priority(Interceptor.Priority.APPLICATION)
public abstract class AuditIsbnGenerator implements IsbnGenerator{
	
	@Inject @Any @Delegate
	private IsbnGenerator isbnGenerator;

	public String next() {
		String isbn = isbnGenerator.next();
		System.out.println("isbngenerator " + this + " generates isbn: " + isbn);
		return isbn;
	}
	
	
	

}
