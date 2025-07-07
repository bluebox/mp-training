package react.casestudy.react.bookselling.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import react.casestudy.react.bookselling.domain.OrderHistory;
import react.casestudy.react.bookselling.domain.Orders;

@Repository
public class OrderRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void createOrder(Orders order) {
        String sql = "INSERT INTO Orders (memberId, totalCost, purchaseDate) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, order.getMemberId(), order.getTotalCost(), order.getPurchaseDate());
    }

    public int getLastOrderId() {
        return jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
    }

    public int getBookQuantity(int bookId) {
        String sql = "SELECT quantity FROM Book WHERE bookId = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, bookId);
    }

    public void decrementBookQuantity(int bookId, int qty) {
        String sql = "UPDATE Book SET quantity = quantity - ? WHERE bookId = ?";
        jdbcTemplate.update(sql, qty, bookId);
    }

    public void insertOrderHistory(OrderHistory item) {
        String sql = "INSERT INTO OrderHistory (orderId, bookId, quantity, totalCost) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, item.getOrderId(), item.getBookId(), item.getQuantity(), item.getTotalCost());
    }

    public List<Orders> getAllOrders() {
        String sql = "SELECT * FROM Orders";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Orders.class));
    }

    public List<OrderHistory> getOrderHistoryByOrderId(int orderId) {
        String sql = "SELECT * FROM OrderHistory WHERE orderId = ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(OrderHistory.class), orderId);
    }
}
