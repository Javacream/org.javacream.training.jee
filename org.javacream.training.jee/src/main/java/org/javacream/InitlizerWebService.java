package org.javacream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/init")
public class InitlizerWebService {

	@PersistenceContext
	private EntityManager entityManager;

	@GET
	@Transactional
	public String init() {
		entityManager.createNativeQuery("drop table if exists ISBN").executeUpdate();
		entityManager.createNativeQuery("drop table if exists MESSAGES").executeUpdate();
		
		entityManager.createNativeQuery("create table ISBN (isbn integer)").executeUpdate();
		entityManager.createNativeQuery("insert into ISBN values (0)").executeUpdate();
		entityManager.createNativeQuery("create table MESSAGES (message varchar(256))").executeUpdate();
		entityManager.createNativeQuery("INSERT INTO STORE (category, item, stock) values ('Book', 'ISBN1', 42)").executeUpdate();
		entityManager.createNativeQuery("INSERT INTO STORE (category, item, stock) values ('Book', 'ISBN2', 43)").executeUpdate();
		entityManager.createNativeQuery("INSERT INTO STORE (category, item, stock) values ('Book', 'ISBN3', 52)").executeUpdate();
		entityManager.createNativeQuery("INSERT INTO STORE (category, item, stock) values ('Book', 'ISBN4', 62)").executeUpdate();
		entityManager.createNativeQuery("INSERT INTO Book (isbn, title, price) values ('ISBN1', 'Title1', 19.99)").executeUpdate();
		entityManager.createNativeQuery("INSERT INTO Book (isbn, title, price) values ('ISBN2', 'Title2', 9.99)").executeUpdate();

		return "OK";
	}
}
