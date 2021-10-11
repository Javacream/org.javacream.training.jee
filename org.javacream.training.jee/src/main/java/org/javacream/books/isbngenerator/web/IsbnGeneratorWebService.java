package org.javacream.books.isbngenerator.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.javacream.books.isbngenerator.impl.RandomIsbnGenerator;

@Path("isbn")
public class IsbnGeneratorWebService {

	//private RandomIsbnGenerator isbnGenerator = new RandomIsbnGenerator(); //FALSCH, KEIN CDI!!!!!
	@Inject private RandomIsbnGenerator isbnGenerator;
	@GET
	public String generateIsbn() {
		return "Generated isbn: " + isbnGenerator.next();
	}
}
