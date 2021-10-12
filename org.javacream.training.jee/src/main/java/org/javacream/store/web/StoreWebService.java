package org.javacream.store.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.javacream.store.api.StoreService;
import org.javacream.util.qualifiers.UseForDi;

@Path("store")
public class StoreWebService {

	@Inject @UseForDi private StoreService storeService;
	
	@GET @Path("{cat}/{item}") public String getStock(@PathParam("cat") String category, @PathParam("item") String item) {
		return "Stock: " + storeService.getStock(category, item);
	}
}