package org.javacream.jpa.demo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/jpa/demo")
public class SimpleJpaDemo {

	@PersistenceContext private EntityManager entityManager;
	@GET
	public String doDemo() {
		return "OK, entityManager=" + entityManager;
	}
}
