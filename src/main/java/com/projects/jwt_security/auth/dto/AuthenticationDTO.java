package com.projects.jwt_security.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticationDTO {
    private String username;
    private String password;
}
