package ru.vlsu.ispi.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.Role;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.repositories.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser (User user)
    {
        String email = user.getEmail();
        if(userRepository.findByEmail(email) != null) return false;
        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }
    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }
    public List<User> list()
    {
        return userRepository.findAll();
    }
    public User show(Long id)
    {
        return userRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id)
    {
        userRepository.deleteById(id);
    }
    //Функция изменения пароля
    public void changeUserPassword(User user, String password)
    {
        user.setPassword(passwordEncoder.encode(password));
    }
    public void save(User user)
    {
        userRepository.save(user);
    }
}
