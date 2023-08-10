package com.projects.jwt_security.user.models;

import com.projects.jwt_security.user.roles.PersonRole;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode(exclude = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private Long yearOfBirth;
    @Enumerated(EnumType.STRING)
    private PersonRole role;
}
