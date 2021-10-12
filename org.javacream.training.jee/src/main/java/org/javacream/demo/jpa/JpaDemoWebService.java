package org.javacream.demo.jpa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("jpa/demo")
public class JpaDemoWebService {

	@PersistenceContext private EntityManager entityManager;
	@GET public String executeDemo() {
		return "OK, entityManager=" + entityManager;
	}
}
