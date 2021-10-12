package org.javacream.books.isbngenerator.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Vetoed;

import org.javacream.books.isbngenerator.api.IsbnGenerator;

@Vetoed
public class CounterIsbnGenerator implements IsbnGenerator {

	private String prefix;
	private String countryCode;
	public String getCountryCode() {
		return countryCode;
	}

	@PostConstruct public void init() {
		counter = 9;
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
