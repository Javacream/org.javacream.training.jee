package org.javacream.web.demo;

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
	
	public EchoWebService() {
		System.out.println("creating EchobWebService " + this);
	}
	@PostConstruct
	public void init() {
		System.out.println("initializing EchobWebService " + this);
	}
	@PreDestroy
	public void destroy() {
		System.out.println("destroying EchobWebService " + this);
	}

	@GET @Produces(MediaType.TEXT_PLAIN) public String ping() {
		System.out.println("ping using " + this);
		return "pong";
	}
}
