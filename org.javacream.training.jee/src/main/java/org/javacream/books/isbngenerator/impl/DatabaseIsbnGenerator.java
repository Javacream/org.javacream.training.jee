package org.javacream.books.isbngenerator.impl;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.javacream.books.isbngenerator.api.IsbnGenerator;

@ApplicationScoped @SequenceStrategy
public class DatabaseIsbnGenerator implements IsbnGenerator {

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
	@Transactional
	public String next(){
		//Lese aktuelle Nummer der ISBN
		//Inkrementieren
		//Zurückschreiben
		return prefix + counter++ + countryCode;
	}

	public String getPrefix(){
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
