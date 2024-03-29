package ru.vlsu.ispi.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class MainController {
    @GetMapping("/")
    public String helloGfg(Principal principal, Authentication auth, Model model) {
        // Get the Username
        String userName = principal.getName();
        System.out.println("Current Logged in User is: " + userName);

        // Get the User Roles/Authorities
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        System.out.println("Current Logged in User Roles are: " + authorities);

        model.addAttribute("username", userName);
        model.addAttribute("roles", authorities);

        return "hello-gfg";
    }
}
