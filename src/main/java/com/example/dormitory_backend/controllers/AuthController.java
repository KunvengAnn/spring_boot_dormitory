package com.example.dormitory_backend.controllers;

import com.example.dormitory_backend.models.RegistrationRequest;
import com.example.dormitory_backend.models.Role;
import com.example.dormitory_backend.models.User;
import com.example.dormitory_backend.repositories.UserRepository;
import com.example.dormitory_backend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        try {
            // Check if user already exists
            if (userRepository.findByEmail(registrationRequest.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Email is already in use.");
            }

            // Create a new user
            User user = new User();
            user.setFirstName(registrationRequest.getFirstName());
            user.setLastName(registrationRequest.getLastName());
            user.setEmail(registrationRequest.getEmail());
            user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            user.setRole(Role.valueOf(registrationRequest.getRole().toUpperCase()));

            // Generate a unique token for the user
            String token = UUID.randomUUID().toString();
            user.setToken(token);

            userRepository.save(user);

            return ResponseEntity.ok("User registered successfully. Token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while registering the user.");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody User loginUser) {
        try {
            // Validate input fields
            if (loginUser.getEmail() == null || loginUser.getPassword() == null ||
                    loginUser.getEmail().isEmpty() || loginUser.getPassword().isEmpty()) {
                return ResponseEntity.badRequest().body("Email and password are required.");
            }

            if (!userRepository.findByEmail(loginUser.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body("Please register an account first.");
            }

            // Authenticate user
            String token = authService.loginUser(loginUser.getEmail(), loginUser.getPassword(), loginUser.getToken());
            if (token == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials.");
            }

            return ResponseEntity.ok("Login successful. Token: " + token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while logging in.");
        }
    }

    @DeleteMapping("/delete/{id_user}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id_user, @RequestHeader("token") String token) {
        String response = authService.deleteUser(id_user, token);
        if (response.equals("User deleted successfully")) {
            return ResponseEntity.ok(response);
        } else if (response.equals("Invalid token")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
