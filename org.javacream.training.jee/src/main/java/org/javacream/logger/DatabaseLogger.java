package org.javacream.logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@ApplicationScoped
public class DatabaseLogger {

	@PersistenceContext private EntityManager entityManager;
	
	@Transactional(TxType.REQUIRES_NEW)
	public void logRequiresNew(String message) {
		entityManager.createNativeQuery("insert into MESSAGES values ('" + message + "')").executeUpdate();
		//throw new RuntimeException("EXPECTED ROLLBACK");
	}
	@Transactional(TxType.REQUIRED)
	public void logRequired(String message) {
		entityManager.createNativeQuery("insert into MESSAGES values ('" + message + "')").executeUpdate();
		//throw new RuntimeException("EXPECTED ROLLBACK");
	}

	public void log(String message, boolean optional) {
		if (optional) {
			logRequiresNew(message);
		}else {
			logRequired(message);
		}
	}
}
