package org.javacream.util.database;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class DatabaseLogger {

	@PersistenceContext private EntityManager entityManager;
	
	@Inject DatabaseLogger delegate;
	@Transactional(Transactional.TxType.REQUIRED)
	public void log(String message) {
		delegate.logWithRequired(message);
		//logWithRequiresNew(message);
	}
	
	@Transactional(Transactional.TxType.REQUIRED)
	private void logWithRequired(String message) {
		Query query =  entityManager.createNativeQuery("insert into messages values (:message)");
		query.setParameter("message", message);
		query.executeUpdate();
		boolean shouldRollback = false;
		if (shouldRollback) throw new RuntimeException("TEST ROLLBACK");

	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	private void logWithRequiresNew(String message) {
		Query query =  entityManager.createNativeQuery("insert into messages values (:message)");
		query.setParameter("message", message);
		query.executeUpdate();
		boolean shouldRollback = true;
		if (shouldRollback) throw new RuntimeException("TEST ROLLBACK");

	}

}
