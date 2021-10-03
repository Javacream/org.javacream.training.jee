package org.javacream.store.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.store.api.StoreService;
import org.javacream.store.api.StoreService.DatabaseStrategy;

@Path("store")
public class StoreWebService {

	@Inject @DatabaseStrategy private StoreService storeService;
	@GET @Path("{cat}/{item}")@Produces(MediaType.TEXT_PLAIN) public String getStock(@PathParam("cat") String category, @PathParam("item") String item) {
		return "Stock: " + storeService.getStock(category, item);
	}
}
