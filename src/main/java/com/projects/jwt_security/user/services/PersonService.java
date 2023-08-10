package com.projects.jwt_security.user.services;

import com.projects.jwt_security.user.exceptions.UserNotFoundException;
import com.projects.jwt_security.user.models.Person;
import com.projects.jwt_security.user.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonService {
    private final PersonRepository personRepository;

    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    public void save(Person person) {
        personRepository.save(person);
    }

    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
}
