package com.edutrack.backend.controller;

import com.edutrack.backend.dto.AuthResponse;
import com.edutrack.backend.dto.LoginRequest;
import com.edutrack.backend.dto.RegisterRequest;
import com.edutrack.backend.dto.StatusUpdateRequest;
import com.edutrack.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = authService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<Map<String, String>> logout() {
        return ResponseEntity.ok(Map.of("message", "Logged out successfully"));
    }

    @PutMapping("/users/{email}/status")
    public ResponseEntity<Map<String, String>> updateUserStatus(
            @PathVariable String email,
            @RequestBody StatusUpdateRequest request) {
        authService.updateUserStatusByEmail(email, request.getStatus());
        return ResponseEntity.ok(Map.of("message", "Status updated successfully"));
    }

    @DeleteMapping("/users/{email}")
    public ResponseEntity<Map<String, String>> deleteUser(@PathVariable String email) {
        authService.deleteUserByEmail(email);
        return ResponseEntity.ok(Map.of("message", "User deleted successfully"));
    }
}
