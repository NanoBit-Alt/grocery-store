package com.market.domain.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record UserView(long id, @NotBlank String userName, @NotBlank @Email String email, @NotBlank String password) {
}
