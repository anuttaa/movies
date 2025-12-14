package com.coco.jumboomovies.controllers;

import com.coco.jumboomovies.model.AppConstants;
import com.coco.jumboomovies.model.authentication.LoginRequest;
import com.coco.jumboomovies.model.authentication.LoginResponse;
import com.coco.jumboomovies.model.authentication.RegisterRequest;
import com.coco.jumboomovies.services.AuthenticationService;
import com.coco.jumboomovies.services.ConfirmationTokenService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/movies/auth")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ConfirmationTokenService tokenService;

    @Autowired
    private HttpServletRequest httpRequest;

    @Autowired
    private MessageSource messageSource;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        try {
            Locale userLocale = httpRequest.getLocale();
            authenticationService.register(request, userLocale);
            String successMessage = messageSource.getMessage("auth.register.success", null, userLocale);
            return ResponseEntity.status(HttpStatus.CREATED).body(successMessage);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error during registration " + ex.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        try {
            String accessToken = authenticationService.login(loginRequest, response);
            LoginResponse loginResponse = new LoginResponse("Login successful", accessToken);
            // Return the response with a status of 200 OK
            return ResponseEntity.ok(loginResponse);
        } catch (Exception e) {
            String errorMessage = messageSource.getMessage("auth.login.error", new Object[]{e.getMessage()}, httpRequest.getLocale());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse(errorMessage));
        }
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshToken(@CookieValue(value = AppConstants.REFRESH_TOKEN, required = false) String refreshToken, HttpServletResponse response) {
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("Refresh token is missing", null));
        }
        try {
            LoginResponse loginResponse = authenticationService.refreshAccessToken(refreshToken, response);
            return ResponseEntity.ok(new LoginResponse(loginResponse.accessToken(), loginResponse.refreshToken(), "Token refreshed successfully"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponse("Invalid refresh token", null));
        }
    }

    @GetMapping("/confirm")
    public ResponseEntity<String> confirmEmail(@RequestParam("token") String token) {
        tokenService.confirmToken(token);
        String successMessage = messageSource.getMessage("auth.confirm.success", null, httpRequest.getLocale());
        return ResponseEntity.ok(successMessage);
    }

}
