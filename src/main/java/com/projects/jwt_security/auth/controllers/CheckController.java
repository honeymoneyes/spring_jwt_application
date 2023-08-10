package com.projects.jwt_security.auth.controllers;

import com.projects.jwt_security.auth.response.JwtResponse;
import com.projects.jwt_security.security.services.PersonDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("/checkAuthenticatedUser")
    public ResponseEntity<?> checkAuth() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        PersonDetails principal = (PersonDetails) authentication.getPrincipal();
        return ResponseEntity.ok(JwtResponse.builder()
                .username(principal.getUsername())
                .role(principal.getAuthorities().toString())
                        .jwt_token("")
                .build());
    }
}
