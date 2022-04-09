package com.ponkratov.busmanagementserver.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class SigninRequest {
    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
