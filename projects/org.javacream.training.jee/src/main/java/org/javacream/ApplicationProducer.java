package org.javacream;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.isbngenerator.impl.CounterIsbnGenerator;

@ApplicationScoped
public class ApplicationProducer {

	@PostConstruct public void init() {
		System.out.println("initializing ApplicationProducer");
	}
	//Outjection
	@Produces public String demo() {
		return new String("Demo"); 
	}
	
	@Produces @SequenceStrategy public IsbnGenerator sequenceIsbnGenerator(CounterIsbnGenerator sequenceIsbnGenerator) {
		//CounterIsbnGenerator sequenceIsbnGenerator = new CounterIsbnGenerator();
		sequenceIsbnGenerator.setPrefix("ISBN:");
		sequenceIsbnGenerator.setCountryCode("-is");
		return sequenceIsbnGenerator; 
	}
}
