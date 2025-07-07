package react.casestudy.react.bookselling.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Orders {
    private int orderId;
    private int memberId;
    private BigDecimal totalCost;
    private Date purchaseDate;

    // Default constructor
    public Orders() {}

    // All-args constructor
    public Orders(int orderId, int memberId, BigDecimal totalCost, Date purchaseDate) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.totalCost = totalCost;
        this.purchaseDate = purchaseDate;
    }
    
    public Orders(int memberId, BigDecimal totalCost, Date purchaseDate) {
        this.memberId = memberId;
        this.totalCost = totalCost;
        this.purchaseDate = purchaseDate;
    }

    // Getters and Setters

    public int getOrderId() {
        return orderId;
    }
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    public int getMemberId() {
        return memberId;
    }
    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }
    public BigDecimal getTotalCost() {
        return totalCost;
    }
    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }
    public Date getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    
    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", memberId=" + memberId +
                ", totalCost=" + totalCost + ", purchaseDate=" + purchaseDate + "]";
    }
}
