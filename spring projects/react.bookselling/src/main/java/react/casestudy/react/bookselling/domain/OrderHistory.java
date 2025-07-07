package react.casestudy.react.bookselling.domain;

import java.math.BigDecimal;

public class OrderHistory {
    // Composite key (orderId, bookId)
    private int orderId;
    private int bookId;
    private int quantity;
    private BigDecimal totalCost;

    // Default constructor
    public OrderHistory() {}

    // All-args constructor
    public OrderHistory(int orderId, int bookId, int quantity, BigDecimal totalCost) {
        this.orderId = orderId;
        this.bookId = bookId;
        this.quantity = quantity;
        this.totalCost = totalCost;
    }

    // Getters and Setters
    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }
    public BigDecimal getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
    
    @Override
    public String toString() {
        return "OrderHistory [orderId=" + orderId + ", bookId=" + bookId +
               ", quantity=" + quantity + ", totalCost=" + totalCost + "]";
    }
}

