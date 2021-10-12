package org.javacream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("init")
public class InitializerWebService {

	@PersistenceContext private EntityManager entityManager;
	@GET public String doInit() {
		entityManager.createNativeQuery("drop table if exists ISBN");
		return "OK";
	}
}
