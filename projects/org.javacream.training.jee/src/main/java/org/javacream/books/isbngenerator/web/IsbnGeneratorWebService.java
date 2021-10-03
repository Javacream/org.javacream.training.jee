package org.javacream.books.isbngenerator.web;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.RandomStrategy;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;


@Path("isbn")
@ApplicationScoped
public class IsbnGeneratorWebService {

	@Inject @RandomStrategy
	private IsbnGenerator randomIsbnGenerator;
	@Inject @SequenceStrategy
	private IsbnGenerator sequenceIsbnGenerator;

	@GET
	@Path("{strategy}")
	@Produces(MediaType.TEXT_PLAIN)
	public String next(@PathParam("strategy") String strategy) {
		if ("random".equals(strategy)) {
			return randomIsbnGenerator.next();
		}
		else {
			return sequenceIsbnGenerator.next();
		}
	}
}
