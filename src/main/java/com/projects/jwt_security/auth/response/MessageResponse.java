package com.projects.jwt_security.auth.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageResponse {
    private String response;
    private String jwt_token;

    public MessageResponse(String response) {
        this.response = response;
    }

    public MessageResponse(String response, String token) {
        this.response = response;
        this.jwt_token = token;
    }
}
