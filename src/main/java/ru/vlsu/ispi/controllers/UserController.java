package ru.vlsu.ispi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vlsu.ispi.dto.UserUpdateDTO;
import ru.vlsu.ispi.dto.UserUpdatePADTO;
import ru.vlsu.ispi.models.Role;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.models.Wallet;
import ru.vlsu.ispi.services.UserService;
import ru.vlsu.ispi.services.WalletService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final WalletService walletService;

    public UserController(UserService userService, WalletService walletService) {
        this.userService = userService;
        this.walletService = walletService;
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
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id)
    {
        userService.deleteById(id);
        return "redirect:/users";
    }
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id)
    {
        List<Role> allRoles = new ArrayList<>();
        allRoles.addAll(Arrays.asList(Role.values()));

        UserUpdateDTO userUpdateDTO = new UserUpdateDTO();
        User user = userService.show(id);
        userUpdateDTO.setId(id);
        userUpdateDTO.setName(user.getName());
        userUpdateDTO.setEmail(user.getEmail());
        userUpdateDTO.setPhoneNumber(user.getPhoneNumber());
        userUpdateDTO.setRoles(user.getRoles());
        userUpdateDTO.setActive(user.isActive());

        model.addAttribute("userUpdateDTO", userUpdateDTO);
        model.addAttribute("allRoles", allRoles);
        return "user/edit.html";
    }
    @PostMapping("/update")
    public String update(Model model, @Valid @ModelAttribute("userUpdateDTO") UserUpdateDTO userUpdateDTO, @Valid @ModelAttribute("UserUpdatePADTO") UserUpdatePADTO userUpdatePADTO, BindingResult bindingResult) throws IOException
    {
        if(userUpdatePADTO == null)
        {
            if(bindingResult.hasErrors())
            {
                List<Role> allRoles = new ArrayList<>();
                allRoles.addAll(Arrays.asList(Role.values()));

                model.addAttribute("userUpdateDTO", userUpdateDTO);
                model.addAttribute("allRoles", allRoles);
                return "user/edit.html";
            }
            User user = new User();
            user.setId(userUpdateDTO.getId());
            user.setName(userUpdateDTO.getName());
            user.setEmail(userUpdateDTO.getEmail());
            user.setPhoneNumber(userUpdateDTO.getPhoneNumber());
            user.setRoles(userUpdateDTO.getRoles());
            user.setActive(userUpdateDTO.isActive());
            user.setPassword(userService.show(user.getId()).getPassword());
            userService.save(user);
        }
        else
        {
            if(bindingResult.hasErrors())
            {
                List<Role> allRoles = new ArrayList<>();
                allRoles.addAll(Arrays.asList(Role.values()));

                model.addAttribute("userUpdatePADTO", userUpdatePADTO);
                model.addAttribute("allRoles", allRoles);
                return "userMainPages/personalAccount.html";
            }
            User user = new User();
            User reqUser = userService.show(userUpdatePADTO.getId());
            user.setId(userUpdatePADTO.getId());
            user.setName(userUpdatePADTO.getName());
            user.setEmail(userUpdatePADTO.getEmail());
            user.setPhoneNumber(userUpdatePADTO.getPhoneNumber());
            user.setRoles(reqUser.getRoles());
            user.setActive(reqUser.isActive());
            user.setPassword(reqUser.getPassword());
            userService.save(user);
        }
        return "redirect:/users";
    }
    @PostMapping("/topUp")
    public String topUp(@ModelAttribute("wallet") Wallet wallet)
    {
        walletService.topUp(wallet);
        return "userMainPages/personalAccount.html";
    }
}
