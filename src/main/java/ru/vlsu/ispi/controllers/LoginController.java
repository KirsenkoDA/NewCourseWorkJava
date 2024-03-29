package ru.vlsu.ispi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/customLogin")
    public String customLogin() {
        return "custom-login";
    }
}
