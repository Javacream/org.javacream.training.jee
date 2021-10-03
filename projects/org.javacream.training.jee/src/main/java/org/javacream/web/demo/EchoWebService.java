package org.javacream.web.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

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
