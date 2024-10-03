package com.example.learnUserAuthentication.demo.Controllers;

import com.example.learnUserAuthentication.demo.Entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginDto) {
        // Authenticate the user
        if ("user@example.com".equals(loginDto.getEmail()) && "password".equals(loginDto.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User registrationDto) {
        // Simulate user registration logic
        return ResponseEntity.ok("User registered successfully");
    }
}

