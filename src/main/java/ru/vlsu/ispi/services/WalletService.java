package ru.vlsu.ispi.services;

import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.Wallet;
import ru.vlsu.ispi.repositories.WalletRepository;

@Service
public class WalletService {
    private final WalletRepository walletRepository;

    public WalletService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }
    public void topUp(Wallet wallet)
    {
        walletRepository.save(wallet);
    }
}
