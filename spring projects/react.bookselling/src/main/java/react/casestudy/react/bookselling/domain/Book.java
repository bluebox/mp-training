package react.casestudy.react.bookselling.domain;

import java.math.BigDecimal;
import java.util.Date;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String categroy;
    private BigDecimal cost;
    private int quantity;
    private Date published;

    // Default constructor
    public Book() {}

    // All-args constructor
    public Book(int bookId, String title, String author, String categroy, BigDecimal cost, int quantity, Date published) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.categroy = categroy;
        this.cost = cost;
        this.quantity = quantity;
        this.published = published;
    }
    
    public Book(String title, String author, String categroy, BigDecimal cost, int quantity, Date published) {
        this.title = title;
        this.author = author;
        this.categroy = categroy;
        this.cost = cost;
        this.quantity = quantity;
        this.published = published;
    }

    // Getters and Setters

    public int getBookId() {
        return bookId;
    }
    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getCategroy() {
        return categroy;
    }
    public void setCategroy(String categroy) {
        this.categroy = categroy;
    }
    public BigDecimal getCost() {
        return cost;
    }
    public void setCost(BigDecimal cost) {
        this.cost = cost;
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
    public Date getPublished() {
        return published;
    }
    public void setPublished(Date published) {
        this.published = published;
    }
    
    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author
                + ", categroy=" + categroy + ", cost=" + cost + ", quantity=" + quantity
                + ", published=" + published + "]";
    }
}

