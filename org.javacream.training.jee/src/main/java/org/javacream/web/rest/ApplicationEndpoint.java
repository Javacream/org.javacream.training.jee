package org.javacream.web.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.application.Application;

@Path("app")
@ApplicationScoped
public class ApplicationEndpoint {

	@Inject
	private Application app;
	
	@Inject
	private DataSource dataSource;
	
	@PersistenceContext private EntityManager entityManager;
	public void setApp(Application app) {
		this.app = app;
	}


	@GET @Produces(MediaType.TEXT_PLAIN)
	public String run() {
		return app.run() + "this: " + this.hashCode() + ", app: " + app.hashCode() + ", ds=" + dataSource + ", em: " + entityManager;
	}
}
