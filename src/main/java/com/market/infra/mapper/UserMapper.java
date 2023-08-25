package com.market.infra.mapper;

import javax.enterprise.context.ApplicationScoped;

import com.market.app.model.auth.AuthenticatedUser;
import com.market.domain.user.User;
import com.market.domain.user.UserView;

@ApplicationScoped
public class UserMapper {

    public UserView mapToUserView(User user) {
        return new UserView(user.getId(), user.getUserName(), user.getEmail(), user.getPassword(), user.getRoles());
    }

    public AuthenticatedUser mapToAuthenticatedUser(User user) {
        return new AuthenticatedUser(user.getId(), user.getUserName(), user.getEmail());
    }

}
