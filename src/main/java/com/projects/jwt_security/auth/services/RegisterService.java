package com.projects.jwt_security.auth.services;

import com.projects.jwt_security.user.models.Person;
import com.projects.jwt_security.user.repositories.PersonRepository;
import com.projects.jwt_security.user.roles.PersonRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public void registerUser(Person person) {
        person.setPassword(passwordEncoder.encode(person.getPassword()));
        person.setRole(PersonRole.USER);
        personRepository.save(person);
    }
}
