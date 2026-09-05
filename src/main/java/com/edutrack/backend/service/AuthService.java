package com.edutrack.backend.service;

import com.edutrack.backend.dto.AuthResponse;
import com.edutrack.backend.dto.LoginRequest;
import com.edutrack.backend.dto.RegisterRequest;
import com.edutrack.backend.entity.User;
import com.edutrack.backend.exception.BadRequestException;
import com.edutrack.backend.exception.ResourceNotFoundException;
import com.edutrack.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already registered..please login to continue....");
        }

        String role = (request.getRole() != null && !request.getRole().isBlank()) 
                ? request.getRole() : "teacher";

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(role)
                .status("active")
                .build();

        User savedUser = userRepository.save(user);

        return AuthResponse.builder()
                .id(savedUser.getId().toString())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .status(savedUser.getStatus())
                .message("Registration successful")
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadRequestException("INVALID ...."));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("INVALID ....");
        }

        if ("blocked".equalsIgnoreCase(user.getStatus())) {
            throw new BadRequestException("Account is blocked. Please contact admin.");
        }

        return AuthResponse.builder()
                .id(user.getId().toString())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .status(user.getStatus())
                .message("Login successful")
                .build();
    }

    @Transactional
    public void updateUserStatusByEmail(String email, String status) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        user.setStatus(status);
        userRepository.save(user);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        if (userRepository.existsByEmail(email)) {
            userRepository.deleteByEmail(email);
        }
    }
}
