package org.javacream.books.isbngenerator.impl;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.SequenceStrategy;
import org.javacream.util.qualifiers.Config;

@ApplicationScoped
@SequenceStrategy
public class DatabaseIsbnGenerator implements IsbnGenerator {
	@Inject
	@Config(property = "isbngenerator.prefix")
	private String prefix;
	@Inject
	@Config(property = "isbngenerator.countryCode")
	private String countryCode;
	@PersistenceContext
	private EntityManager entityManager;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}

	@Transactional(TxType.REQUIRES_NEW)
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
