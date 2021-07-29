package com.web.communicator.service;

import com.web.communicator.domain.Token;
import com.web.communicator.domain.User;
import com.web.communicator.repository.TokenRepo;
import com.web.communicator.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class UserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenRepo tokenRepo;
    @Autowired
    private MailService mailService;

    public void addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setEmail(user.getEmail());
//        user.setPhone(user.getPhone());
//        user.setFirstname(user.getFirstname());
//        user.setLastname(user.getLastname());
        user.setRole("ROLE_USER");
        userRepo.save(user);
        sendToken(user);
    }

    public void saveUser(User user) {
        userRepo.save(user);
    }

    private void sendToken(User user) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setUser(user);
        token.setValue(tokenValue);
        tokenRepo.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue;
        try {
            mailService.sendMail(user.getEmail(), "Potwierdź rejestrację", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
