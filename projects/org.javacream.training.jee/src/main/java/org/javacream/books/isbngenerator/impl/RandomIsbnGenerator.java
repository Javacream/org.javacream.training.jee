package org.javacream.books.isbngenerator.impl;

import java.util.Random;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.util.Prod;
@RequestScoped
@Prod
public class RandomIsbnGenerator implements IsbnGenerator {

	private String prefix;
	private String countryCode;
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}
	private Random random;
	
	@PostConstruct public void init(){
		random = new Random(this.hashCode() + System.currentTimeMillis());
		prefix = "ISBN:";
		countryCode = "-dk";
		System.out.println("initializing " + this);

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
