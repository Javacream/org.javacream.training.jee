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

		return "OK";
	}
}
