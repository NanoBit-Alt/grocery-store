package com.market.app.service;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;

import com.market.app.model.auth.AuthenticationOutput;
import com.market.app.model.auth.LoginInput;
import com.market.domain.user.User;
import com.market.domain.user.UserUseCase;
import com.market.infra.mapper.UserMapper;
import com.market.infra.security.TokenService;
import com.market.infra.security.session.AuthSession;
import com.market.infra.security.session.AuthSessionService;
import io.quarkus.security.UnauthorizedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ApplicationScoped
public class AuthenticatorService {

    private final UserMapper mapper;
    private final UserUseCase userUseCase;
    private final TokenService tokenService;
    private final AuthSessionService authSessionService;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Inject
    public AuthenticatorService(UserUseCase userUseCase, TokenService tokenService, AuthSessionService authSessionService, UserMapper mapper) {
        this.mapper = mapper;
        this.userUseCase = userUseCase;
        this.tokenService = tokenService;
        this.authSessionService = authSessionService;
    }

    public AuthenticationOutput login(LoginInput loginInput, String ip) throws Exception {
        var user = userUseCase.findByEmail(loginInput.email());
        if (user == null) throw new NotFoundException();

        if (validatePassword(loginInput, user)) {
            return new AuthenticationOutput(mapper.mapToAuthenticatedUser(user), authenticate(user, ip));
        } else {
            throw new UnauthorizedException();
        }
    }

    public String authenticate(User user, String ip) throws Exception {
        var jwt = tokenService.generateJwt(user);
        AuthSession authSession = generateAuthSession(user, ip);
        authSessionService.create(authSession);

        return jwt;
    }

    public AuthSession generateAuthSession(User user, String ip) {
        AuthSession authSession = new AuthSession();
        authSession.setIpLogged(ip);
        authSession.setUserName(user.getUserName());
        authSession.setEmail(user.getEmail());
        return authSession;
    }

    private boolean validatePassword(LoginInput loginInput, User user) {
        return passwordEncoder.matches(loginInput.password(), user.getPassword());
    }
}