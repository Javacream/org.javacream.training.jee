package org.javacream.books.isbngenerator.impl;

import javax.enterprise.context.ApplicationScoped;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.qualifiers.SequenceStrategy;

@ApplicationScoped
@SequenceStrategy
public class CounterIsbnGenerator implements IsbnGenerator {

	private String prefix;
	private String countryCode;
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}
	private int counter;
	public String next(){
		return prefix + counter++ + countryCode;
	}

	public String getPrefix(){
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
