package com.web.communicator.config;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Hello {

    @RequestMapping("/hello")
    public String sayHello() {
        return "Hello";
    }

    @RequestMapping("/hello2")
    public String sayHello2() {
        return "Hello2";
    }

/*    @RequestMapping("/login")
    public String login(Map<String, Object> model) {
        return "jetbrains://idea/navigate/reference?project=communicator&path=templates/login.html";
    }*/
}
