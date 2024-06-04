package ru.vlsu.ispi.services;

import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.SalesLine;
import ru.vlsu.ispi.models.SalesTable;
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
    public void buyProduct(Wallet wallet, SalesTable salesTable)
    {
        int amount = 0;
        for(SalesLine salesLine: salesTable.getSalesLines())
        {
            amount += salesLine.getQuantity() * salesLine.getPrice();
        }
        if(wallet.getBalance() - amount < 0)
        {
            throw new RuntimeException();
        }
        else
        {
            wallet.setBalance(wallet.getBalance() - amount);
            walletRepository.save(wallet);
        }
    }
}
