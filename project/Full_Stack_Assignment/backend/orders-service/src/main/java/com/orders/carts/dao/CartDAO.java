package com.orders.carts.dao;

import java.util.List;

import com.orders.carts.domain.Cart;
import com.orders.carts.domain.CartItem;

public interface CartDAO {
    Long createCart(Long userId);
    void addItemToCart(CartItem item);
    List<CartItem> getItemsByCartId(Long cartId);
    void removeItem(Long cartItemId);
    void clearCart(Long cartId);
    Cart getCartByUserId(Long userId);
}
