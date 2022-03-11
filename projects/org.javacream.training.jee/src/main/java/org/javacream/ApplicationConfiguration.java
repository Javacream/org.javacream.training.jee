package org.javacream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.util.Config;

@ApplicationScoped
public class ApplicationConfiguration {

	@PostConstruct public void init() {
		System.out.println("initializing ApplicationProducer");
	}
	//Outjection
	@Produces public String demo() {
		return new String("Demo"); 
	}

	@Produces @Config(property = "isbngenerator.prefix") String prefix() {
		return "ISBN:"; 
	}
	@Produces @Config(property = "isbngenerator.countryCode") String countryCode() {
		return "-de"; 
	}

	
}
