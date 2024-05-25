package ru.vlsu.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vlsu.ispi.models.Product;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.models.Wallet;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    @Query("SELECT w FROM Wallet w WHERE w.user.id = :userId")
    Wallet findByUserId(@Param("userId") Long userId);
}
