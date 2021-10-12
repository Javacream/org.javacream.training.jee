package org.javacream.books.isbngenerator.impl;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Vetoed;

import org.javacream.books.isbngenerator.api.IsbnGenerator;

//@RequestScoped
@Vetoed
public class RandomIsbnGenerator implements IsbnGenerator {

	private String prefix;
	private String countryCode;
	public String getCountryCode() {
		return countryCode;
	}

	@PostConstruct public void init() {
		prefix = "ISBN:";
		countryCode = "-de";
	}
	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}
	private Random random;
	
	{
		random = new Random(this.hashCode() + System.currentTimeMillis());
	}
	
	public String next(){
		return prefix + random.nextInt() + countryCode;
	}

	public String getPrefix(){
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
