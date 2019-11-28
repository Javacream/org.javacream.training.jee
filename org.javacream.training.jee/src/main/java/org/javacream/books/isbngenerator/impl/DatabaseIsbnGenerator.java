package org.javacream.books.isbngenerator.impl;

import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.javacream.books.isbngenerator.api.IsbnGenerator;
import org.javacream.books.isbngenerator.api.IsbnGenerator.IsbnGeneratorQualifier;
import org.javacream.util.aspect.Traced;
import org.javacream.util.database.DatabaseLogger;

@IsbnGeneratorQualifier(IsbnGenerator.IsbnGeneratorStrategy.SEQUENCE)
@ApplicationScoped
public class DatabaseIsbnGenerator implements IsbnGenerator {
	@PersistenceContext
	private EntityManager entityManager;

	@Inject
	private DatabaseLogger databaseLogger;

	private String prefix;
	private String countryCode;

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String suffix) {
		this.countryCode = suffix;
	}

	@Traced
	@Transactional(Transactional.TxType.REQUIRED)
	public String next() {
		int actualKey = (int) entityManager.createNativeQuery("select key from keys").getSingleResult();
		actualKey++;
		Query query = entityManager.createNativeQuery("update keys set key = :key");
		query.setParameter("key", actualKey);
		query.executeUpdate();
		try {
			databaseLogger.log("created new key at " + new Date());
		} catch (Exception e) {
			// OK...
		}
		return prefix + actualKey + countryCode;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
