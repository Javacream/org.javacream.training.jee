package org.javacream.books.warehouse.web;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import org.javacream.books.warehouse.api.BookException;
import org.javacream.books.warehouse.api.BooksService;

import java.util.ArrayList;
import java.util.List;

@Path("books")
public class BooksWebService {
    @Inject @BooksService.DatabaseStrategy BooksService booksService;
    @POST @Produces (MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON) public List<String> create(List<String> titles) throws WebApplicationException {
        try {
            List<String> generated = new ArrayList<>();
            for (String title : titles) {
                generated.add(booksService.newBook(title));
            }
            return generated;
        }
        catch(BookException be){
            throw new WebApplicationException(Response.Status.BAD_REQUEST);
        }
    }
    @Path("{isbn}")
    @GET @Produces(MediaType.APPLICATION_JSON)  public Response findById(@PathParam("isbn") String isbn){
        Response response = null;
        try {
            response = Response.ok().entity(booksService.findBookByIsbn(isbn)).build();
        } catch (BookException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return response;
    }
}
