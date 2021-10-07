package org.javacream;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.books.isbngenerator.impl.SequenceIsbnGenerator;
import org.javacream.util.Config;
import org.javacream.util.stages.Prod;

public class ApplicationProducer {


	@Produces
	@Config(property = "isbngenerator.prefix")
	String prefix() {
		return "ISBN:";
	}
	@Produces
	@Config(property = "isbngenerator.countryCode")
	String suffix() {
		return "-dk";
	}

	@Produces
	@SequenceStrategy @Prod @ApplicationScoped
	IsbnGenerator isbngeneratorSequence(SequenceIsbnGenerator sequenceIsbnGenerator ) {
		sequenceIsbnGenerator.setPrefix("ISBN:");
		sequenceIsbnGenerator.setCountryCode("-de");
		return sequenceIsbnGenerator;
	}

}
