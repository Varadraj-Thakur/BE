package com.example.sharesphere.controller;

import com.example.sharesphere.model.Users;
import com.example.sharesphere.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return service.register(user);
    }

//changes
    // ✅ UPDATED PART — Added /login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Users user) {

        if (user.getUsername() == null || user.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Username and password are required");
        }

        Users authenticated = service.authenticate(user.getUsername(), user.getPassword());

        if (authenticated != null) {
            authenticated.setPassword(null); // Hide password when returning
            return ResponseEntity.ok(authenticated);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid credentials");
        }
    }
}
