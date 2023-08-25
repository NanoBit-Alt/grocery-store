package com.market.app.model.auth;

public record AuthenticationOutput(AuthenticatedUser user, String token) {
}
