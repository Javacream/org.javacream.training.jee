package org.javacream.web.demo;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("database")
@ApplicationScoped
public class DatabaseWebService {
	@PersistenceContext private EntityManager entityManager;
	@GET @Produces(MediaType.TEXT_PLAIN) public String ping() {
		System.out.println("entityManager=" + entityManager);
		return "OK";
	}
}
