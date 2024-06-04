package ru.vlsu.ispi.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vlsu.ispi.models.SalesTable;
import ru.vlsu.ispi.models.User;

@Service
public class PayService {
    private final SalesTableService salesTableService;
    private final WalletService walletService;

    public PayService(SalesTableService salesTableService, WalletService walletService) {
        this.salesTableService = salesTableService;
        this.walletService = walletService;
    }

    @Transactional
    public void pay(SalesTable salesTable, User user)
    {
        walletService.buyProduct(user.getWallet(), salesTable);
        salesTableService.save(salesTable);
    }
}
