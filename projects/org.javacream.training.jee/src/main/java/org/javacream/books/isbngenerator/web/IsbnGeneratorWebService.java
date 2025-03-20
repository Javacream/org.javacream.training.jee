package org.javacream.books.isbngenerator.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.javacream.books.isbngenerator.api.IsbnGenerator;

@Path("isbn")
public class IsbnGeneratorWebService {
    @Inject @IsbnGenerator.RandomStrategy
    private IsbnGenerator isbnGenerator;

    @POST
    @Produces(MediaType.TEXT_PLAIN) public String generate(){
        return isbnGenerator.next();
    }
}
