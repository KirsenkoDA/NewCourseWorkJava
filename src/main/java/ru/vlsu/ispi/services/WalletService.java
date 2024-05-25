package ru.vlsu.ispi.services;

import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.models.Wallet;
import ru.vlsu.ispi.repositories.WalletRepository;

@Service
public class WalletService {
    private final WalletRepository walletRepository;
    private final UserService userService;

    public WalletService(WalletRepository walletRepository, UserService userService) {
        this.walletRepository = walletRepository;
        this.userService = userService;
    }
    public void topUp(Wallet wallet, User user)
    {
        Wallet walletUpdate = walletRepository.findByUserId(user.getId());
        walletUpdate.setBalance(wallet.getBalance() + walletUpdate.getBalance());
        walletRepository.save(walletUpdate);
    }
}
