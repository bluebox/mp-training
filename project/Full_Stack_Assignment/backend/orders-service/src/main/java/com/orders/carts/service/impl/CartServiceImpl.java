package com.orders.carts.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orders.carts.dao.CartDAO;
import com.orders.carts.domain.Cart;
import com.orders.carts.domain.CartItem;
import com.orders.carts.service.CartService;
import com.orders.service.OrderService;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDAO cartDAO;
    
    @Autowired
    private OrderService orderService;

    @Override
    public Cart getOrCreateCart(Long userId) {
        Cart cart = cartDAO.getCartByUserId(userId);
        if (cart == null) {
            Long cartId = cartDAO.createCart(userId);
            cart = new Cart(cartId, userId, null, null, null);
        }
        return cart;
    }

    @Override
    public void addItemToCart(Long userId, Long productId, Integer quantity,BigDecimal price,String productName) {
        Cart cart = getOrCreateCart(userId);
        CartItem item = new CartItem();
        item.setCartId(cart.getCartId());
        item.setProductId(productId);
        item.setUserId(userId);
        item.setQuantity(quantity);
        item.setPrice(price);
        item.setProductName(productName);
        cartDAO.addItemToCart(item);
    }

    @Override
    public List<CartItem> getCartItems(Long userId) {
        Cart cart = getOrCreateCart(userId);
        return cartDAO.getItemsByCartId(cart.getCartId());
    }

    @Override
    public void removeItem(Long cartItemId) {
        cartDAO.removeItem(cartItemId);
    }

    @Override
    public void clearCart(Long userId) {
        Cart cart = getOrCreateCart(userId);
        cartDAO.clearCart(cart.getCartId());
    }
    
    @Override
    public void checkout(Long userId,String address) {
        List<CartItem> items = getCartItems(userId);
        if (items.isEmpty()) {
            throw new IllegalStateException("Cart is empty.");
        }

        orderService.createOrderFromCartItems(userId, items,address);

        clearCart(userId);
    }
}
