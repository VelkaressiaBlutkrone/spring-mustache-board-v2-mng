package com.example.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @GetMapping("/join")
    public String userJoin(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/login")
    public String login(@RequestParam String param) {
        return new String();
    }

}
