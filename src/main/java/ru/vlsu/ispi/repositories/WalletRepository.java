package ru.vlsu.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vlsu.ispi.models.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
}
