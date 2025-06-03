package banking;

import java.math.BigDecimal;

public final class Transaction {
    public void setRoutingNumber(int routingNumber) {
        this.routingNumber = routingNumber;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    private int routingNumber;
    private int customerId;
    private long transactionId;
    private BigDecimal transactionAmount;

    public Transaction(int routingNumber, int customerId, long transactionId, BigDecimal transactionAmount) {
        this.routingNumber = routingNumber;
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
    }

    public int getRoutingNumber() {
        return routingNumber;
    }

    public int getCustomerId() {
        return customerId;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "routingNumber=" + routingNumber +
                ", customerId=" + customerId +
                ", transactionId=" + transactionId +
                ", transactionAmount=" + transactionAmount +
                '}';
    }
}