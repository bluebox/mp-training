package com.orders.carts.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orders.carts.domain.AddItemRequest;
import com.orders.carts.domain.CartItem;
import com.orders.carts.domain.CheckoutRequest;
import com.orders.carts.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/{userId}/items")
    public List<CartItem> getCartItems(@PathVariable Long userId) {
        return cartService.getCartItems(userId);
    }

    @PostMapping("/add")
    public String addItem(@RequestBody AddItemRequest request) {
        cartService.addItemToCart(request.getUserId(), request.getProductId(), request.getQuantity(), request.getPrice(), request.getProductName());
        return "Item added to cart.";
    }

    @DeleteMapping("/item/{cartItemId}")
    public String removeItem(@PathVariable Long cartItemId) {
        cartService.removeItem(cartItemId);
        return "Item removed from cart.";
    }

    @DeleteMapping("/{userId}/clear")
    public String clearCart(@PathVariable Long userId) {
        cartService.clearCart(userId);
        return "Cart cleared.";
    }
    
    @PostMapping("/{userId}/checkout")
    public String checkout(@PathVariable Long userId, @RequestBody CheckoutRequest checkoutRequest) {
        cartService.checkout(userId, checkoutRequest.getAddress());
        return "Order placed successfully.";
    }
}
