package com.projects.jwt_security.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    private String username;
    private String password;
    private Long yearOfBirth;
}
