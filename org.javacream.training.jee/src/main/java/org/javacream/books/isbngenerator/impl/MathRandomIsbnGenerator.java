package org.javacream.books.isbngenerator.impl;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.RandomStrategy;
import org.javacream.util.qualifiers.Config;

@RequestScoped
@RandomStrategy
public class MathRandomIsbnGenerator implements IsbnGenerator {

	@Inject @Config(property="isbngenerator.prefix") private String prefix;
	@Inject @Config(property="isbngenerator.countryCode") private String countryCode;
	public String getCountryCode() {
		return countryCode;
	}

//	@PostConstruct public void init() {
//		prefix = "ISBN:";
//		countryCode = "-de";
//	}
	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}
	
	public String next(){
		return prefix + Math.random() + countryCode;
	}

	public String getPrefix(){
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
