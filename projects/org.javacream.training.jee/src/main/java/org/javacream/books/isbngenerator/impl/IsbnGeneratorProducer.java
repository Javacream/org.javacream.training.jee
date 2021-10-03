package org.javacream.books.isbngenerator.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.util.Config;

@ApplicationScoped
public class IsbnGeneratorProducer {
	@Produces
	@SequenceStrategy
	public IsbnGenerator sequenceIsbnGenerator(CounterIsbnGenerator sequenceIsbnGenerator, @Config(property = "isbngenerator.prefix") String prefix) {
		// CounterIsbnGenerator sequenceIsbnGenerator = new CounterIsbnGenerator();
		sequenceIsbnGenerator.setPrefix(prefix);
		sequenceIsbnGenerator.setCountryCode("-is");
		return sequenceIsbnGenerator;
	}
}
