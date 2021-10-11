package org.javacream.web.echo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("echo")
@ApplicationScoped
public class EchoWebService {

//	public EchoWebService () {
//		System.out.println("constructing " + this);
//	}
	

	@PostConstruct
	public void initEchoWebService() {
		System.out.println("initializing " + this);
	}

	@PreDestroy
	public void cleanUpEchoWebService() {
		System.out.println("destroying " + this);
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
		System.out.println("pinging using " + this);

		return "pong";
	}
}
