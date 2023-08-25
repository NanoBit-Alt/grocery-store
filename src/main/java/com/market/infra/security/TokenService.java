package com.market.infra.security;

import java.security.PrivateKey;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.market.app.model.auth.Role;
import com.market.domain.user.User;
import com.market.infra.security.session.AuthSessionService;
import io.smallrye.jwt.build.Jwt;
import io.smallrye.jwt.build.JwtClaimsBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import static io.smallrye.jwt.util.KeyUtils.readPrivateKey;

@ApplicationScoped
public class TokenService {

    private final String issuer;

    private final int tokenDuration;

    private String privateKeyLocation;

    private AuthSessionService authSessionService;

    @Inject
    public TokenService(@ConfigProperty(name = "mp.jwt.verify.issuer") String issuer,
                        @ConfigProperty(name = "app.jwt.expiration-time") int tokenDuration, @ConfigProperty(name = "smallrye.jwt.sign.key.location") String privateKeyLocation, AuthSessionService authSessionService) {
        this.issuer = issuer;
        this.tokenDuration = tokenDuration;
        this.privateKeyLocation = privateKeyLocation;
        this.authSessionService = authSessionService;
    }

    public String generateJwt(User user) throws Exception {
        JwtClaimsBuilder claimsBuilder = Jwt.claims();

        claimsBuilder.issuer(issuer);
        claimsBuilder.subject(String.valueOf(user.getId()));
        if(user.getAuthorityLevel()==1) {
            claimsBuilder.groups(Role.ADMIN.name());
        }else if(user.getAuthorityLevel()==2){
            claimsBuilder.groups(Role.EMPLOYER.name());
        }else if(user.getAuthorityLevel()==3){
            claimsBuilder.groups(Role.USER.name());
        }
        var now = System.currentTimeMillis() / 1000;
        claimsBuilder.issuedAt(now);
        claimsBuilder.expiresAt(now + tokenDuration);


        PrivateKey pk = readPrivateKey("privateKey.pem");

        return claimsBuilder.jws().signatureKeyId("privateKey.pem").sign(pk);
    }

}