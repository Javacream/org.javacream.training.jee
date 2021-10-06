package org.javacream.logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@ApplicationScoped
public class DatabaseLogger {

	@PersistenceContext
	private EntityManager entityManager;
	@Inject
	DatabaseLogger self;

	@Transactional(TxType.REQUIRES_NEW)
	public void logRequiresNew(String message) {
		entityManager.createNativeQuery("insert into MESSAGES values ('" + message + "')").executeUpdate();
		throw new RuntimeException("EXPECTED ROLLBACK");
	}

	@Transactional(TxType.REQUIRED)
	public void logRequired(String message) {
		entityManager.createNativeQuery("insert into MESSAGES values ('" + message + "')").executeUpdate();
		// throw new RuntimeException("EXPECTED ROLLBACK");
	}

	@Transactional(TxType.REQUIRED)
	public void log(String message, boolean optional) {
		if (optional) {
			try {
				self.logRequiresNew(message);
			} catch (RuntimeException e) {
				System.out.println("catched exception from DatabaseLogger");
			}

		} else {
			self.logRequired(message);
		}
	}
}
