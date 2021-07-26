package com.web.communicator.config;

import com.web.communicator.domain.User;
import com.web.communicator.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class Start {

    @Autowired
    private UserRepo userRepo;

    public Start(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("user1"));
        user.setRole("ROLE_ADMIN");
        user.setEnabled(true);
        userRepo.save(user);
    }
}
