package com.orders.carts.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.orders.carts.dao.CartDAO;
import com.orders.carts.domain.Cart;
import com.orders.carts.domain.CartItem;

@Repository
public class CartDAOImpl implements CartDAO {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    private RowMapper<Cart> cartMapper = (rs, rowNum) -> {
        Cart cart = new Cart();
        cart.setCartId(rs.getLong("CartId"));
        cart.setUserId(rs.getLong("UserId"));
        return cart;
    };

    private RowMapper<CartItem> cartItemMapper = (rs, rowNum) -> {
        CartItem item = new CartItem();
        item.setCartItemId(rs.getLong("CartItemId"));
        item.setCartId(rs.getLong("CartId"));
        item.setProductId(rs.getLong("ProductId"));
        item.setQuantity(rs.getInt("Quantity"));
        item.setPrice(rs.getBigDecimal("Price"));
        item.setProductName(rs.getString("ProductName"));
        item.setUserId(rs.getLong("UserId"));
        item.setAddedAtDate(rs.getTimestamp("AddedAtDate") != null ? rs.getTimestamp("AddedAtDate").toLocalDateTime() : null);
        return item;
    };

    @Override
    public Long createCart(Long userId) {
        String sql = "INSERT INTO carts (UserId) VALUES (:userId)";
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("userId", userId);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(sql, params, keyHolder, new String[] { "CartId" });

        return keyHolder.getKey().longValue();
    }

    @Override
    public void addItemToCart(CartItem item) {
        String sql = "INSERT INTO cart_items (CartId, ProductId, UserId, Quantity, Price, ProductName) " +
                     "VALUES (:cartId, :productId, :userId, :quantity, :price, :productName)";

        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("cartId", item.getCartId())
                .addValue("productId", item.getProductId())
                .addValue("userId", item.getUserId())
                .addValue("quantity", item.getQuantity())
                .addValue("price", item.getPrice())
        		.addValue("productName",item.getProductName());
        System.out.println(params);
        jdbcTemplate.update(sql, params);
    }

    @Override
    public List<CartItem> getItemsByCartId(Long cartId) {
        String sql = "SELECT * FROM cart_items WHERE CartId = :cartId";
        return jdbcTemplate.query(sql, new MapSqlParameterSource("cartId", cartId), cartItemMapper);
    }

    @Override
    public void removeItem(Long cartItemId) {
        String sql = "DELETE FROM cart_items WHERE CartItemId = :cartItemId";
        jdbcTemplate.update(sql, new MapSqlParameterSource("cartItemId", cartItemId));
    }

    @Override
    public void clearCart(Long cartId) {
        String sql = "DELETE FROM cart_items WHERE CartId = :cartId";
        jdbcTemplate.update(sql, new MapSqlParameterSource("cartId", cartId));
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        String sql = "SELECT * FROM carts WHERE UserId = :userId";
        List<Cart> carts = jdbcTemplate.query(sql, new MapSqlParameterSource("userId", userId), cartMapper);
        return carts.isEmpty() ? null : carts.get(0);
    }
}
