package ru.vlsu.ispi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.services.UserService;

import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration()
    {
        return "user/registration.html";

    }
    @GetMapping("/login")
    public String login()
    {
        return "user/login";
    }
    @PostMapping("/registration")
    public String createUser(User user, Model model) throws IOException
    {
        if(!userService.createUser(user))
        {
            model.addAttribute("errorMessage", "Пользователь с таким e-mail " + user.getEmail() + " уже существует");
            model.addAttribute("user", user);
            return "user/registration.html";
        }
        return "redirect:login";
    }
    @GetMapping("/accessDenied")
    public String accessDenied()
    {
        return "user/accessDenied.html";
    }
    @GetMapping()
    public String index(Model model)
    {
        model.addAttribute("users", userService.list());
        return "user/index.html";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable Long id, Model model)
    {
        User user = userService.show(id);
        model.addAttribute("user", user);
//        model.addAttribute("avatar", user.getAvatar());
        return "user/show.html";
    }
//    @DeleteMapping("/{id}")
//    public String delete(@PathVariable Long id)
//    {
//        userService.deleteById(id);
//        return "redirect:/users";
//    }
//    @GetMapping("/{id}/edit")
//    public String edit(Model model, @PathVariable("id") Long id)
//    {
//        List<Role> allRoles = new ArrayList<>();
//        allRoles.addAll(Arrays.asList(Role.values()));
//        model.addAttribute("user", userService.show(id));
//        model.addAttribute("allRoles", allRoles);
//        return "user/edit.html";
//    }
//    @PatchMapping("/{id}")
//    public String update(@ModelAttribute("user") User user) throws IOException
//    {
////        if(bindingResult.hasErrors())
////        {
////            return "user/edit.html";
////        }
////        userService.changeUserPassword(user, user.getPassword());
//        user.setPassword(userService.show(user.getId()).getPassword());
//        userService.save(user);
//        return "redirect:/users";
//    }
}
