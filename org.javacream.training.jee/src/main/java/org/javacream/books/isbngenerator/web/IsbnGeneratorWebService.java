package org.javacream.books.isbngenerator.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;

@Path("isbn")
@ApplicationScoped
public class IsbnGeneratorWebService {

	// private RandomIsbnGenerator isbnGenerator = new RandomIsbnGenerator();
	// //FALSCH, KEIN CDI!!!!!
	@Inject
	private RandomIsbnGenerator isbnGenerator;

	{
		System.out.println("constructing " + ", reference=" + isbnGenerator  + ", this=" + this);
	}

	@PostConstruct public void init() {
		System.out.println("initializing "  + ", reference=" + isbnGenerator + ", this=" + this);
		
	}
	
	@GET
	public String generateIsbn() {
		return "Generated isbn: " + isbnGenerator.next() + ", reference=" + isbnGenerator + ", this=" + this;
	}
}
