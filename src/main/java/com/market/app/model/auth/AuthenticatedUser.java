package com.market.app.model.auth;

public record AuthenticatedUser(
        long id,
        String name,
        String email
) {
}
