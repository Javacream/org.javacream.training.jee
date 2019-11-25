package org.javacream.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.application.Application;

@Path("app")
public class ApplicationEndpoint {

	@GET @Produces(MediaType.TEXT_PLAIN)
	public String run() {
		return Application.run();
	}
}
