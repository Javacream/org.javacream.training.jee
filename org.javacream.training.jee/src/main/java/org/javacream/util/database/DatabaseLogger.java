package org.javacream.util.database;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class DatabaseLogger {

	@PersistenceContext private EntityManager entityManager;
	@Transactional(Transactional.TxType.REQUIRED)
	public void log(String message) {
		Query query =  entityManager.createNativeQuery("insert into messages values (:message)");
		query.setParameter("message", message);
		query.executeUpdate();
		boolean shouldRollback = false;
		if (shouldRollback) throw new RuntimeException("TEST ROLLBACK");
	}
	
}
