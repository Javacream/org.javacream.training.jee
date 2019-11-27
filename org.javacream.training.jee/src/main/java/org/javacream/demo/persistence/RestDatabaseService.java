package org.javacream.demo.persistence;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("db")
public class RestDatabaseService {

	@Inject private DatabaseUtility dbUtility;
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("demo")
	public String execTransactionScript() {
		dbUtility.executeTransactionScript();
		return "OK";
	}
}
