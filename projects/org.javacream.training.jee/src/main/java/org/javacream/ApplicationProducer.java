package org.javacream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class ApplicationProducer {

	@PostConstruct public void init() {
		System.out.println("initializing ApplicationProducer");
	}
	//Outjection
	@Produces public String demo() {
		return new String("Demo"); 
	}
	
}
