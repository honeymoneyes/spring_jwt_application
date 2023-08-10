package com.projects.jwt_security.security.services;

import com.projects.jwt_security.user.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class PersonDetails implements UserDetails {

    private final Person person;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getAuthoritiesByRole(person);
    }

    @Override
    public String getUsername() {
        return person.getUsername();
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<SimpleGrantedAuthority> getAuthoritiesByRole(Person person) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + person.getRole().toString()));
    }
}
