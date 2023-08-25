package com.market.infra.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.market.infra.security.session.AuthSessionService;

@ApplicationScoped
public class TaskScheduler {

    private AuthSessionService authSessionService;

    @Inject
    public TaskScheduler(AuthSessionService authSessionService) {
        this.authSessionService = authSessionService;
    }
/**
 @Scheduled(every="60s") public void sessionCleaner(){
 List<AuthSession> authSessionList = authSessionService.getAll();
 for(AuthSession authSession : authSessionList){
 if(authSessionService.find(authSession.getId()).getExpirationTime().compareTo(Instant.now())<=0){
 authSessionService.deleteById(authSession.getId());
 }
 }
 }
 **/

}