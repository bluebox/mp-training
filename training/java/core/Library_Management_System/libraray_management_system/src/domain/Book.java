package domain;

import domain.checking_enum.Availability;
import domain.checking_enum.Status;

public class Book {
    private int bookid;
    private String title;
    private String author;
    private String category;
    private Status status;
    private Availability availability;

    public Book(int bookid, String title, String author, String category, Status status, Availability availability) {
        this.bookid = bookid;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }

    public Book(String title, String author, String category, Status status, Availability availability) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Book [bookid=" + bookid + ", title=" + title + ", author=" + author + ", category=" + category +
                ", status=" + status + ", availability=" + availability + "]";
    }

    // Getters and Setters
    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
}
