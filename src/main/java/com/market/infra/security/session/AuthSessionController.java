package com.market.infra.security.session;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Path("session")
public class AuthSessionController {

    private AuthSessionService authSessionService;

    @Inject
    public AuthSessionController(AuthSessionService authSessionService) {
        this.authSessionService = authSessionService;
    }

    @GET
    @Path("get-all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<AuthSession> getAll() {
        return authSessionService.getAll();
    }

}