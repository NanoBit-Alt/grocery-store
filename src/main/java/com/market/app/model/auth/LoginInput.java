package com.market.app.model.auth;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record LoginInput(@NotBlank @Email String email, @NotBlank String password) {
}
