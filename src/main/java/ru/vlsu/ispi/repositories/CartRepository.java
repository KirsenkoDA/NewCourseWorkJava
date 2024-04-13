package ru.vlsu.ispi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.vlsu.ispi.models.Cart;
import ru.vlsu.ispi.models.Product;
import ru.vlsu.ispi.models.User;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT c FROM Cart c WHERE c.user = :user")
    List<Cart> findAllByUser(@Param("user") User user);
    Cart findByUserAndProduct(User user, Product product);
}
