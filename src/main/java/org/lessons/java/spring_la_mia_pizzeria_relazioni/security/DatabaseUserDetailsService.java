package org.lessons.java.spring_la_mia_pizzeria_relazioni.security;

import java.util.Optional;

import org.lessons.java.spring_la_mia_pizzeria_relazioni.model.User;
import org.lessons.java.spring_la_mia_pizzeria_relazioni.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class DatabaseUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> userAttempt = repository.findByUsername(username);
        if (userAttempt.isEmpty()) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new DatabaseUserDetails(userAttempt.get());
    }
}
