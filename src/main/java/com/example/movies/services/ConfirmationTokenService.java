package com.example.movies.services;

import com.example.movies.entities.ConfirmationToken;
import com.example.movies.entities.User;
import com.example.movies.repositories.ConfirmationTokenRepository;
import com.example.movies.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConfirmationTokenService {

    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final UserRepository userRepository;

    public ConfirmationToken generateConfirmationToken(User user) {
        String token = UUID.randomUUID().toString();
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setToken(token);
        confirmationToken.setUser(user);
        confirmationToken.setCreatedAt(LocalDateTime.now());
        confirmationToken.setExpiresAt(LocalDateTime.now().plusDays(1));
        return confirmationTokenRepository.save(confirmationToken);
    }

    public ConfirmationToken validateConfirmationToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenRepository.findByToken(token)
                .orElseThrow(() -> new IllegalStateException("Invalid confirmation token"));
        if (confirmationToken.getExpiresAt().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Expired confirmation token");
        }
        return confirmationToken;
    }

    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = validateConfirmationToken(token);
        confirmationToken.setConfirmedAt(LocalDateTime.now());
        confirmationTokenRepository.save(confirmationToken);

        User currentUser = confirmationToken.getUser();
        currentUser.setEmailVerified(true);
        userRepository.save(currentUser);
    }
}
