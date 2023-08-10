package com.projects.jwt_security.auth.controllers;

import com.projects.jwt_security.auth.dto.AuthenticationDTO;
import com.projects.jwt_security.auth.response.JwtResponse;
import com.projects.jwt_security.auth.response.MessageResponse;
import com.projects.jwt_security.auth.services.RegisterService;
import com.projects.jwt_security.security.jwt.JwtUtils;
import com.projects.jwt_security.security.services.PersonDetails;
import com.projects.jwt_security.user.dto.PersonDTO;
import com.projects.jwt_security.user.models.Person;
import com.projects.jwt_security.user.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtils jwtUtils;
    private final ModelMapper modelMapper;
    private final RegisterService registerService;
    private final AuthenticationManager authenticationManager;
    private final PersonRepository personRepository;

    @PostMapping("/registration")
    public ResponseEntity<?> registerPerson(@RequestBody PersonDTO personDTO) {
        Person person = convertToPerson(personDTO);

        if (personRepository.existsByUsername(person.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!", "Denied"));
        }

        registerService.registerUser(person);

        String token = jwtUtils.generateToken(person.getUsername());

        return ResponseEntity.ok(new MessageResponse(
                "User registered successfully!", token));
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginPerson(@RequestBody AuthenticationDTO authenticationDTO) {
        UsernamePasswordAuthenticationToken inputUser =
                new UsernamePasswordAuthenticationToken(authenticationDTO.getUsername(),
                        authenticationDTO.getPassword());

        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(inputUser);
        } catch (BadCredentialsException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Incorrect credentials!", "Denied"));
        }
        PersonDetails personDetails = (PersonDetails) authenticate.getPrincipal();

        String token = jwtUtils.generateToken(authenticationDTO.getUsername());

        return ResponseEntity.ok(JwtResponse.builder()
                .username(personDetails.getUsername())
                .role(personDetails.getAuthorities().toString())
                .jwt_token(token)
                .build());
    }


    private Person convertToPerson(PersonDTO personDTO) {
        return this.modelMapper.map(personDTO, Person.class);
    }
}
