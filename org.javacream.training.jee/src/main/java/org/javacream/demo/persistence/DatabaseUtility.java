package org.javacream.demo.persistence;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class DatabaseUtility {
	
	@PersistenceContext EntityManager entityManager;
	
	@Transactional(Transactional.TxType.REQUIRED)
	public void executeTransactionScript() {
		Query query = entityManager.createNativeQuery("drop table messages if exists");
		query.executeUpdate();
		query = entityManager.createNativeQuery("create table messages (message varchar(256))");
		query.executeUpdate();
		query = entityManager.createNativeQuery("insert into messages values ('Hello')");
		query.executeUpdate();
		query = entityManager.createNativeQuery("select message from messages");
		List<String> messages = query.getResultList();
		messages.forEach(System.out::println);
		query = entityManager.createNativeQuery("select message from messages where message like :filter");
		query.setParameter("filter", "H%");
		messages = query.getResultList();
		messages.forEach(System.out::println);
	}

}
