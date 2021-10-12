package org.javacream.books.isbngenerator.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.RandomStrategy;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;

@Path("isbn")
@ApplicationScoped
public class IsbnGeneratorWebService {

	@Inject @RandomStrategy	private IsbnGenerator randomIsbnGenerator;
	@Inject @SequenceStrategy private IsbnGenerator sequenceIsbnGenerator;

	
	@GET @Path("seq")
	public String generateSequenceIsbn() {
		return "Generated isbn: " + sequenceIsbnGenerator.next();
	}
	@GET @Path("rnd")
	public String generateRandomIsbn() {
		return "Generated isbn: " + randomIsbnGenerator.next();
	}
}
