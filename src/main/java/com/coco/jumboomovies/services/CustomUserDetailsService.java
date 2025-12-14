package com.coco.jumboomovies.services;

import com.coco.jumboomovies.entities.User;
import com.coco.jumboomovies.model.SecurityUser;
import com.coco.jumboomovies.repositories.UserRepository;
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

