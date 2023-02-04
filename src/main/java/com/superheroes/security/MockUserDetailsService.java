package com.superheroes.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
public class MockUserDetailsService implements UserDetailsService  {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var users = Map.of(
                "ldoglioli", "USER"
        );
        var role = users.get(username);
        if (role != null) {
            User.UserBuilder userBuilder = User.withUsername(username);
            var passwordEncoder = new BCryptPasswordEncoder();
            var encryptedPassword = passwordEncoder.encode("luciano");
            userBuilder.password(encryptedPassword).roles(role);
            return userBuilder.build();
        } else {
            throw new UsernameNotFoundException(username);
        }

    }
}

