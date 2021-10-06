package org.javacream.logger;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Transactional
@ApplicationScoped
public class DatabaseLogger {

	@PersistenceContext private EntityManager entityManager;
	
	public void log(String message) {
		entityManager.createNativeQuery("insert into MESSAGES values ('" + message + "')").executeUpdate();
	}
}
