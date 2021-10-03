package org.javacream.books.isbngenerator.impl;

import javax.inject.Inject;
import javax.inject.Named;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.util.SequenceIdGenerator;

//@Vetoed
//@Alternative
//@Dev
public class CounterIsbnGenerator implements IsbnGenerator {

	private String prefix;
	private String countryCode;
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}
	@Inject @Named("forIsbnGenerator") private SequenceIdGenerator idGenerator;
	public String next(){
		return prefix + idGenerator.next() + countryCode;
	}

	public String getPrefix(){
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
