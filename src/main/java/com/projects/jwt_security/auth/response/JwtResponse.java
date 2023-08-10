package com.projects.jwt_security.auth.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class JwtResponse {
    private String username;
    private String role;
    private String jwt_token;

    public JwtResponse(String username, String role, String jwt_token) {
        this.username = username;
        this.role = role;
        this.jwt_token = jwt_token;
    }

    public JwtResponse(String username, String role) {
        this.username = username;
        this.role = role;
    }
}
