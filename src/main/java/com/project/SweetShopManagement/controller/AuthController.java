package com.project.SweetShopManagement.controller;

import com.project.SweetShopManagement.model.LoginRequest;
import com.project.SweetShopManagement.model.LoginResponse;
import com.project.SweetShopManagement.model.RegisterRequest;
import com.project.SweetShopManagement.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // ✅ USER REGISTER (PUBLIC)
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        authService.register(request, "user");
        return ResponseEntity.ok("User registered successfully");
    }

    // 🔐 ADMIN REGISTER (ADMIN ONLY)
    @PostMapping("/register/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> registerAdmin(@RequestBody RegisterRequest request) {
        authService.register(request, "admin");
        return ResponseEntity.ok("Admin registered successfully");
    }

    // ✅ LOGIN
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}
