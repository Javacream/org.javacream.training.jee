package org.javacream.web.demo;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.javacream.books.warehouse.api.Book;

@Path("greeter")
public class GreeterWebService {

    @GET @Produces(MediaType.TEXT_PLAIN)
    public String greet(@HeaderParam("name") String name){
        return "Hello " + name;
    }
    @Path("demo")@GET @Produces(MediaType.APPLICATION_JSON)
    public Book greet2(@HeaderParam("name") String name){
        Book b = new Book();
        b.setIsbn("A");
        b.setTitle("B");
        return b;
    }

}
