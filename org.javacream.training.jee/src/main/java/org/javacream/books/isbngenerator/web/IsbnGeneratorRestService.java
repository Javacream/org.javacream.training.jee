package org.javacream.books.isbngenerator.web;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;

@Path("/isbn")
@ApplicationScoped
public class IsbnGeneratorRestService {

	@Inject private CounterIsbnGenerator isbnGenerator;

//	private IsbnGeneratorRestService()
	{
		System.out.println("initializing " + this);
//		 isbnGenerator = new CounterIsbnGenerator();
	}

	@PostConstruct public void initTheGenerator() {
//		isbnGenerator = new CounterIsbnGenerator();
		System.out.println("PostConstruct: " + this);
		
	}
	@PreDestroy public void theGeneratorWillStop() {
		System.out.println("PreDestroy: " + this);
		
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String retrieveNextIsbn() {
		return isbnGenerator.next();
		
	}
}
