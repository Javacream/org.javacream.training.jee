package org.javacream.books.order.web;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.OrderService;

import javax.print.attribute.standard.Media;

@Path("orders")
public class OrderWebService {
    @Inject
    @OrderService.InMemoryStrategy OrderService orderService;

    @POST @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
    public Order order(OrderCreate orderCreate) {
        return orderService.order(orderCreate.getIsbn(), orderCreate.getNumber());
    }
}
