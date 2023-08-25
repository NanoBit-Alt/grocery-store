package com.market.app.controller;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.market.app.model.Order;
import com.market.app.service.OrderService;
import io.quarkus.security.Authenticated;
import io.quarkus.vertx.web.Body;

@ApplicationScoped
@Path("order")
public class OrderController {

    private OrderService orderService;

    @Inject
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GET
    @Path("user/{id}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrdersByUserId(@PathParam("id") long id) {
        return Response.ok(orderService.getUserOrders(id)).build();
    }

    @POST
    @Path("create")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid @Body Order order) {
        return Response.ok(orderService.create(order)).build();
    }

    @DELETE
    @Path("{id}")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        orderService.delete(id);
        return Response.ok().build();
    }
}