package com.market.infra.security.session;

import javax.enterprise.context.ApplicationScoped;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class AuthSessionRepository implements PanacheRepository<AuthSession> {

    public AuthSession findByEmail(String email) {
        return find("email", email).firstResult();
    }

    public AuthSession create(AuthSession authSession) {
        persist(authSession);
        return authSession;
    }
}