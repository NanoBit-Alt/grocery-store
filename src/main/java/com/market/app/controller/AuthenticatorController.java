package com.market.app.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.market.app.model.auth.LoginInput;
import com.market.app.service.AuthenticatorService;
import io.quarkus.vertx.web.Body;
import io.vertx.core.http.HttpServerRequest;

@ApplicationScoped
@Path("auth")
public class AuthenticatorController {

    private AuthenticatorService authenticatorService;

    @Inject
    public AuthenticatorController(AuthenticatorService authenticatorService) {
        this.authenticatorService = authenticatorService;
    }


    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid @Body LoginInput loginInput, @Context HttpServerRequest request) throws Exception {
        String ip = request.remoteAddress().hostAddress();
        return Response.ok(authenticatorService.login(loginInput, ip) + ip).build();
    }
}