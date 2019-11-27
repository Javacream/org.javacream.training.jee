package org.javacream.store.web.rest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.store.api.StoreService;

@ApplicationScoped
@Path("store")
public class StoreWebService {
	
	@Inject
	private StoreService storeService;

	
	//http://localhost:8080/jee/rest/store/books/ISBN42 GET

	@Path("{category}/{item}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public int getStock(@PathParam("category")String category, @PathParam("item")String item) {
		System.out.println("retrieving stock for category=" + category + ", item=" + item);
		return storeService.getStock(category, item);
	}
	
	

}
