package org.javacream.books.isbngenerator.impl;

import java.util.Random;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.RandomStrategy;
import org.javacream.util.Config;

@ApplicationScoped
@RandomStrategy
public class RandomIsbnGenerator implements IsbnGenerator {

	@Inject @Config(property="isbngenerator.prefix")
	private String prefix;
	@Inject @Config(property="isbngenerator.countryCode")
	private String countryCode;
	public String getCountryCode() {
		return countryCode;
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
