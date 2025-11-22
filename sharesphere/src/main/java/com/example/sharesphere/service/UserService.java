package com.example.sharesphere.service;

import com.example.sharesphere.model.Users;
import com.example.sharesphere.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    // Inject the encoder bean instead of creating a new instance
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Users register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    // ✅ UPDATED PART — Added login/authenticate logic
    public Users authenticate(String username, String rawPassword) {
        Users stored = repo.findByUsername(username);
        if (stored == null) return null;

        if (encoder.matches(rawPassword, stored.getPassword())) {
            return stored;
        } else {
            return null;
        }
    }
}
