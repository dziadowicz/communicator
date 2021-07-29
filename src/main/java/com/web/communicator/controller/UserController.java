package com.web.communicator.controller;

import com.web.communicator.domain.Token;
import com.web.communicator.domain.User;
import com.web.communicator.repository.TokenRepo;
import com.web.communicator.repository.UserRepo;
import com.web.communicator.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TokenRepo tokenRepo;

    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        return "login";
    }

    @GetMapping("/sign-up")
    public String signup(Model model) {
        model.addAttribute("user", new User());
        return "sign-up";
    }

    @GetMapping("/error")
    public String sayHello2() {
        return "error";
    }

    @PostMapping("/register")
    public String register(User user, Model model) {
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            model.addAttribute("wrongName", true);
            // todo wrong email
        } else {
            userService.addUser(user);
        }
        return "sign-up";
    }

    @GetMapping("/token")
    public String value(@RequestParam String value) {
//         todo throw if not exist
        Token token = tokenRepo.findByValue(value).get();
        User user = token.getUser();
        user.setEnabled(true);
        userService.saveUser(user);
        if (tokenRepo.findByValue(value).get().getUser().isEnabled()) {
            return "login";
        } else {return "sign-up";}
    }
}
