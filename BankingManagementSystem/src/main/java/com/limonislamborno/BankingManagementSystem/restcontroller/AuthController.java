package com.limonislamborno.BankingManagementSystem.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class AuthController { @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
    String email = credentials.get("email");
    String password = credentials.get("password");

    // Your authentication logic here

    // For simplicity, let's assume authentication is successful
    boolean isAuthenticated = true;

    if (isAuthenticated) {
        return ResponseEntity.ok().body(Map.of("success", true));
    } else {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("success", false));
    }
}
}