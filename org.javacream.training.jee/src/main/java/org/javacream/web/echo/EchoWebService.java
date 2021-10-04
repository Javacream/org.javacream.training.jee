package org.javacream.web.echo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/echo")
public class EchoWebService {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String ping() {
		System.out.println("received ping, instance=" + this +", executor=" + Thread.currentThread());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "pong";
	}
}
