package org.javacream.books.isbngenerator.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.javacream.books.isbngenerator.api.IsbnGenerator;

public class SequenceIsbnGenerator implements IsbnGenerator {

	@PersistenceContext
	private EntityManager entityManager;
	private String prefix;
	private String countryCode;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}

	@Transactional
	public String next() {
		Integer actual = (Integer) entityManager.createNativeQuery("select isbn from ISBN").getResultList().get(0);
		actual++;
		entityManager.createNativeQuery("update ISBN set isbn=" + actual).executeUpdate();
		return prefix + actual + countryCode;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
