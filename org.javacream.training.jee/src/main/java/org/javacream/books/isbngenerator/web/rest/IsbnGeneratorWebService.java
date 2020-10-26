package org.javacream.books.isbngenerator.web.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.IsbnGeneratorQualifier;

@ApplicationScoped
@Path("isbn")
public class IsbnGeneratorWebService {

	@Inject
	@IsbnGeneratorQualifier(IsbnGenerator.IsbnGeneratorStrategy.SEQUENCE)
	private IsbnGenerator isbnGenerator;

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String next() {
		return isbnGenerator.next();
	}

	@Path("demo")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String nextDemo() {
		return isbnGenerator.next();
	}

}
