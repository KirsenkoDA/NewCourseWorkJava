package ru.vlsu.ispi.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.Role;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.models.Wallet;
import ru.vlsu.ispi.repositories.UserRepository;
import ru.vlsu.ispi.repositories.WalletRepository;

import java.util.List;

@Service
public class UserService {
    private final WalletRepository walletRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(WalletRepository walletRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean createUser (User user)
    {
        String email = user.getEmail();
        if (userRepository.findByEmail(email) != null) {
            return false; // Пользователь с таким email уже существует
        }

        user.setActive(true);
        user.getRoles().add(Role.ROLE_USER);

        Wallet wallet = new Wallet();
        wallet.setBalance(0);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        wallet.setUser(user); // Устанавливаем связь между пользователем и кошельком

        userRepository.save(user); // Сначала сохраняем пользователя
        user.setWallet(wallet);
        walletRepository.save(wallet); // Затем сохраняем кошелек

        return true; // Пользователь успешно создан
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
