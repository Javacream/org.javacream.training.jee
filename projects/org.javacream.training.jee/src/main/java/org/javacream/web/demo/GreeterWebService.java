package org.javacream.web.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

@Path("greeter")
public class GreeterWebService {

    @GET @Produces(MediaType.TEXT_PLAIN)
    public String greet(@HeaderParam("name") String name){
        return "Hello " + name;
    }
}
