package com.orders.carts.service;

import java.math.BigDecimal;
import java.util.List;

import com.orders.carts.domain.Cart;
import com.orders.carts.domain.CartItem;

public interface CartService {
    Cart getOrCreateCart(Long userId);
    void addItemToCart(Long userId, Long productId, Integer quantity, BigDecimal price,String productName);
    List<CartItem> getCartItems(Long userId);
    void removeItem(Long cartItemId);
    void clearCart(Long userId);
	void checkout(Long userId, String address);
}
