package com.example.learnUserAuthentication.demo.Controllers;

import com.example.learnUserAuthentication.demo.Entity.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/welcome")
    public ResponseEntity<?> welcome(@AuthenticationPrincipal OAuth2User user) {
        if (user == null) {
            System.out.println("No user is authenticated!");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No user authenticated");
        }

        System.out.println("Logged-in user: " + user.getAttribute("login"));
        return ResponseEntity.ok("User Logged In successfully");
    }

    @GetMapping("/error")
    public ResponseEntity<String> handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
            String message = "Error with status code: " + statusCode;
            System.out.println(message);  // Log the error status code for debugging
            return ResponseEntity.status(statusCode).body(message);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown error occurred.");
    }
}

