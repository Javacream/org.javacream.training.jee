package org.javacream.books.isbngenerator.impl;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.logger.DatabaseLogger;

//@Transactional(TxType.REQUIRED) //Default-Attribut
@Transactional
public class SequenceIsbnGenerator implements IsbnGenerator {

	@PersistenceContext
	private EntityManager entityManager;
	private String prefix;
	private String countryCode;

	@Inject private DatabaseLogger databaseLogger;
	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}

	public String next() {
		Integer actual = (Integer) entityManager.createNativeQuery("select isbn from ISBN").getResultList().get(0);
		actual++;
		databaseLogger.log("created isbn " + actual + " at " + new Date());
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
