package com.example.movies.services;

import com.example.movies.entities.User;
import com.example.movies.model.SecurityUser;
import com.example.movies.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);
        return user
                .map(SecurityUser::new)
                .orElseThrow(() -> new UsernameNotFoundException("( ͡ಠ ʖ̯ ͡ಠ)" + username + " not found"));
    }
}

