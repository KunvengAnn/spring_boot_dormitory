package com.example.dormitory_backend.services;

import com.example.dormitory_backend.models.Role;
import com.example.dormitory_backend.models.User;
import com.example.dormitory_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            return "Email already in use";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.ROLE_USER);
        userRepository.save(user);
        return "User registered successfully";
    }

    public String loginUser(String email, String password, String token) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent() && passwordEncoder.matches(password, existingUser.get().getPassword())) {
            // Validate token if provided
            if (token == null || !token.equals(existingUser.get().getToken())) {
                return null; // Return null if token is invalid
            }
            return existingUser.get().getToken(); // Return valid token
        }

        return null; // Return null if credentials are invalid
    }

    public String deleteUser(Integer id_user, String token) {
        Optional<User> user = userRepository.findById(id_user);
        if (user.isPresent()) {
            if (token.equals(user.get().getToken())) {
                userRepository.delete(user.get());
                return "User deleted successfully";
            } else {
                return "Invalid token";
            }
        }
        return "User not found";
    }

}
