package org.javacream.books.isbngenerator.web;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.books.isbngenerator.api.IsbnGenerator;

@Path("isbn")
@ApplicationScoped
public class IsbnGeneratorWebService {

	@Inject
	private IsbnGenerator isbnGenerator;

	@PostConstruct
	public void init() {
		System.out.println("initializing " + this + "using business " + isbnGenerator + ", business class is "
				+ isbnGenerator.getClass().getName());

	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String next() {
		System.out.println("generating isbn using " + this + ", using business " + isbnGenerator);
		return isbnGenerator.next();
	}
}
