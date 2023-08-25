package com.market.infra.security.session;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

@ApplicationScoped
public class AuthSessionService {

    private final AuthSessionRepository authSessionRepository;

    @Inject
    public AuthSessionService(AuthSessionRepository authSessionRepository) {
        this.authSessionRepository = authSessionRepository;
    }


    @Transactional
    public AuthSession create(AuthSession authSession) {
        return authSessionRepository.create(authSession);
    }

    public AuthSession find(long id) {
        return authSessionRepository.findById(id);
    }

    public List<AuthSession> getAll() {
        return authSessionRepository.listAll();
    }

    @Transactional
    public boolean deleteById(long id) {
        return authSessionRepository.deleteById(id);
    }
}