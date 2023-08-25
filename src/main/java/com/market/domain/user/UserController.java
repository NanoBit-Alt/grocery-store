package com.market.domain.user;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.market.infra.security.session.AuthSessionService;
import io.quarkus.vertx.web.Body;


@ApplicationScoped
@Path("user")
public class UserController {

    private UserService userService;

    private AuthSessionService authSessionService;

    @Inject
    public UserController(UserService userService, AuthSessionService authSessionService) {
        this.userService = userService;
        this.authSessionService = authSessionService;
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOne(@PathParam("id") long id) {
        return Response.ok(userService.findById(id)).build();
    }

    @GET
    @Path("get-all")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPage(@HeaderParam("Authorization") String authHeader, @PathParam("index") int index) {
        return Response.ok(userService.listByPage(index)).build();
    }

    @POST
    @Path("create")
    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(@Valid @Body UserView userView) {
        return Response.ok(userService.create(userView)).build();
    }

    @DELETE
    @Path("{id}")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") long id) {
        return Response.ok(userService.delete(id)).build();
    }

    @PUT
    @Path("update")
    @RolesAllowed("ADMIN")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@Valid @Body UserView userView) {
        return Response.ok(userService.update(userView)).build();
    }

}