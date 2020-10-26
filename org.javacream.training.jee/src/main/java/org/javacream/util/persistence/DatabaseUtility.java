package org.javacream.util.persistence;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class DatabaseUtility {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional(Transactional.TxType.REQUIRED)
	public void executeTransactionScript() {
		Query query = entityManager.createNativeQuery("drop table messages if exists");
		query.executeUpdate();
		query = entityManager.createNativeQuery("create table messages (message varchar(256))");
		query.executeUpdate();
		query = entityManager.createNativeQuery("insert into messages values ('Hello')");
		query.executeUpdate();
		query = entityManager.createNativeQuery("select message from messages");
		@SuppressWarnings("unchecked")
		List<String> messages = query.getResultList();
		messages.forEach(System.out::println);
		query = entityManager.createNativeQuery("select message from messages where message like ?");
		query.setParameter(1, "H%");
		messages = query.getResultList();
		messages.forEach(System.out::println);
	}

	@Transactional(Transactional.TxType.REQUIRED)
	public void initTables() {
		entityManager.createNativeQuery("drop table keys if exists").executeUpdate();
		entityManager.createNativeQuery("drop table store if exists").executeUpdate();
		entityManager.createNativeQuery("create table keys (key integer)").executeUpdate();
		entityManager.createNativeQuery("insert into keys values(1)").executeUpdate();
		entityManager.createNativeQuery(
				"create table store (category varchar(256), item varchar(256), stock integer, primary key (category, item))")
				.executeUpdate();
		entityManager.createNativeQuery("insert into store values('books', 'ISBN1', 42)").executeUpdate();
		entityManager.createNativeQuery("insert into Book (isbn, title, price) values('ISBN1', 'Title1', 19.99)").executeUpdate();
		entityManager.createNativeQuery("insert into Book (isbn, title, price) values('ISBN2', 'Title2', 9.99)").executeUpdate();
		entityManager.createNativeQuery("insert into Book (isbn, title, price) values('ISBN3', 'Title3', 29.99)").executeUpdate();

	}

}
