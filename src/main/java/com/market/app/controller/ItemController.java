package com.market.app.controller;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.market.app.model.Item;
import com.market.app.service.ItemService;
import io.quarkus.security.Authenticated;

@ApplicationScoped
@Path("/item")
public class ItemController {

    private ItemService itemService;

    @Inject
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GET
    @Path("{id}")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") long id) {
        return Response.ok(itemService.getOne(id)).build();
    }

    @GET
    @Path("get-all")
    @Authenticated
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        var prodList = itemService.getAll().toString();
        return Response.ok(prodList).build();
    }

    @DELETE
    @RolesAllowed("ADMIN")
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        return Response.ok(itemService.deleteById(id)).build();
    }

    @POST
    @RolesAllowed("ADMIN")
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Item item) {
        return Response.ok(itemService.create(item)).build();
    }

    @PUT
    @RolesAllowed("ADMIN")
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Item item) {
        return Response.ok(itemService.update(item)).build();
    }
}