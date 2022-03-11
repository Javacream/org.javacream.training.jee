package org.javacream.books.order.web;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.javacream.books.order.api.OrderService;
import org.javacream.books.order.api.OrderService.InMemoryStrategy;

@Path("order")
public class OrderWebService {

	@Inject @InMemoryStrategy OrderService orderService;
	@GET @Produces(MediaType.TEXT_PLAIN) public String doOrder() {
		orderService.order("ISBN1", 10);
		return orderService.allOrders().toString();
	}
}
