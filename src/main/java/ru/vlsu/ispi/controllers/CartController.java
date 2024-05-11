package ru.vlsu.ispi.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.vlsu.ispi.models.*;
import ru.vlsu.ispi.services.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/carts")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;
    private final SalesTableService salesTableService;
    private final SalesLineService salesLineService;
    private final StatusService statusService;

    public CartController(CartService cartService, ProductService productService, SalesTableService salesTableService, SalesLineService salesLineService, StatusService statusService) {
        this.cartService = cartService;
        this.productService = productService;
        this.salesTableService = salesTableService;
        this.salesLineService = salesLineService;
        this.statusService = statusService;
    }

    @GetMapping()
    public String show(@AuthenticationPrincipal User user, Model model)
    {
        List<Cart> carts = cartService.findByUser(user);
        model.addAttribute("carts", carts);
        return "userMainPages/cart";
    }
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id)
    {
        cartService.delete(id);
        return "redirect:/carts";
    }
    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable("id") Long productId, @AuthenticationPrincipal User user)
    {
        Product product = productService.show(productId);
        Cart existingCart = cartService.findToCountProducts(user, product);
        if(cartService.findToCountProducts(user, product)!= null)
        {
            existingCart.setQuantity(existingCart.getQuantity() + 1);
            existingCart.setAmount(existingCart.getPrice() * existingCart.getQuantity());
            cartService.addToCart(existingCart);
        }
        else
        {
            Cart newCart = new Cart();
            newCart.setProduct(product);
            newCart.setUser(user);
            newCart.setPrice(product.getPrice());
            newCart.setQuantity(1);
            newCart.setAmount(newCart.getPrice());
            cartService.addToCart(newCart);
        }
        return "redirect:/carts";
    }
    @PostMapping("/increaseQty/{id}")
    public String increaseQty(@PathVariable("id") Long cartId)
    {
        Cart cart = cartService.show(cartId);
        cart.setQuantity(cart.getQuantity() + 1);
        //Добавить условие, когда добавлю количество товара
        cartService.addToCart(cart);
        return "redirect:/carts";
    }
    @PostMapping("/reduceQty/{id}")
    public String reduceQty(@PathVariable("id") Long cartId)
    {
        Cart cart = cartService.show(cartId);
        int qty = cart.getQuantity() - 1;
        if (qty == 0)
        {
            cartService.delete(cartId);
        }
        else {
            cart.setQuantity(qty);
        }
        //Добавить условие, когда добавлю количество товара
        cartService.addToCart(cart);
        return "redirect:/carts";
    }
    @PostMapping("/makeOrder")
    public String makeOrder(@AuthenticationPrincipal User user)
    {
        //Получаем списко товаров из карзины
        List<Cart> carts = cartService.findByUser(user);
        //Создаём новый заказ
        SalesTable salesTable = new SalesTable();
        //Новый заказ "не оформлен"
        salesTable.setStatus(statusService.show(1));
        salesTable.setUser(user);
        salesTable.setDateCreated(LocalDateTime.now());
        //Сохранение нового заказа
        salesTableService.save(salesTable);
        salesTable.setId(salesTableService.findByStatusAndUser(statusService.show(1), user).getId());
        //Создание строк заказа
        for(Cart cart: carts)
        {
            Product product = cart.getProduct();
            if(cart.getQuantity() < product.getQuantity())
            {
                //Изменение количества товара
                product.setQuantity(product.getQuantity() - cart.getQuantity());
                productService.save(product);
                //Создание строк заказа
                SalesLine salesLine = new SalesLine();
                salesLine.setSalesTable(salesTable);
                salesLine.setProduct(cart.getProduct());
                salesLine.setQuantity(cart.getQuantity());
                salesLine.setPrice(cart.getPrice());
                salesLineService.save(salesLine);
                //Очистка корзины
                cartService.delete(cart.getId());
            }
            else
            {
                return "redirect:/carts";
            }
        }
        return "redirect:/salesTables/" + salesTable.getId().toString();
    }
}
