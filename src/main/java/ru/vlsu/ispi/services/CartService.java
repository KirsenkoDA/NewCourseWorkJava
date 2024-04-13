package ru.vlsu.ispi.services;

import org.springframework.stereotype.Service;
import ru.vlsu.ispi.models.Cart;
import ru.vlsu.ispi.models.Product;
import ru.vlsu.ispi.models.User;
import ru.vlsu.ispi.repositories.CartRepository;

import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public Cart findToCountProducts(User user, Product product)
    {
        return cartRepository.findByUserAndProduct(user, product);
    }
    public void addToCart(Cart cart){
        cartRepository.save(cart);
    }
    public List<Cart> findByUser (User user)
    {
        return cartRepository.findAllByUser(user);
    }
    public void delete(Long id){cartRepository.deleteById(id);}
}
