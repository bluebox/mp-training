package PustakaLokam.library.model;

import PustakaLokam.library.enums.AvailabilityStatus;
import PustakaLokam.library.enums.BookCondition;

public class Book {
    private Integer bookID;
    private String title;
    private String author;
    private String category;
    private BookCondition condition;
    private AvailabilityStatus availability;

    public Book() {
    };

    public Book(Integer bookID, String title, String author, String category,
            BookCondition condition, AvailabilityStatus availability) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.category = category;
        this.condition = condition;
        this.availability = availability;
    }

    public Integer getBookID() {
        return bookID;
    }

    public void setBookID(Integer bookID) {
        this.bookID = bookID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null) {
            this.title = title;
        } else {
            this.title = "";
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        if (author != null) {
            this.author = author;
        } else {
            this.author = "";
        }
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category != null) {
            this.category = category;
        } else {
            this.category = "";
        }
    }

    public BookCondition getCondition() {
        return condition;
    }

    public void setCondition(BookCondition condition) {
        if (condition != null) {
            this.condition = condition;
        } else {
            this.condition = BookCondition.ACTIVE; // we have given default status of a book as ACTIVE
        }
    }

    public AvailabilityStatus getAvailability() {
        return availability;
    }

    public void setAvailability(AvailabilityStatus availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Book ID=" + bookID +
                ", Title='" + title + '\'' +
                ", Author='" + author + '\'' +
                ", Category='" + category + '\'' +
                ", Condition=" + condition +
                ", Availability=" + availability +
                '}';
    }
}
