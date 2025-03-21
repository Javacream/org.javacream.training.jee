package org.javacream.books.order.web;

import jakarta.inject.Inject;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.javacream.books.order.api.Order;
import org.javacream.books.order.api.OrderService;
import java.util.List;

@Path("orders")
public class OrderWebService {
    @Inject
    @OrderService.InMemoryStrategy OrderService orderService;

    @POST @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
    public Order order(OrderCreate orderCreate) {
        return orderService.order(orderCreate.getIsbn(), orderCreate.getNumber());
    }

    @Path("json")
    @POST @Produces(MediaType.APPLICATION_JSON) @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "create order")
    public Order order2(@RequestBody(
            description = "Order to create", required = true,
            content = @Content(mediaType = "application/json",
                    example = "{ \"isbn\": \"ISBN1\", \"title\": \"Title\" }")) JsonObject orderCreate) {
        return orderService.order(orderCreate.getString("isbn"), orderCreate.getInt("number"));
    }
    @GET @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "find all orders")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Order.class)) }),
            @APIResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @APIResponse(responseCode = "404", description = "Book not found",
                    content = @Content) })

    public List<Order> allOrders() {
        return orderService.allOrders();
    }
}
