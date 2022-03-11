package org.javacream.books.isbngenerator.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;

@ApplicationScoped
public class IsbnGeneratorProducer {
	@Produces @SequenceStrategy public IsbnGenerator sequenceIsbnGenerator(CounterIsbnGenerator sequenceIsbnGenerator) {
		//CounterIsbnGenerator sequenceIsbnGenerator = new CounterIsbnGenerator();
		sequenceIsbnGenerator.setPrefix("ISBN:");
		sequenceIsbnGenerator.setCountryCode("-is");
		return sequenceIsbnGenerator; 
	}
}
