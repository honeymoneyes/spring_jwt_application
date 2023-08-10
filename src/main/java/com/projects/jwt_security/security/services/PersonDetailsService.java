package com.projects.jwt_security.security.services;

import com.projects.jwt_security.user.exceptions.UserNotFoundException;
import com.projects.jwt_security.user.models.Person;
import com.projects.jwt_security.user.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonDetailsService implements UserDetailsService {

    private final PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person person = personRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        return new PersonDetails(person);
    }
}
