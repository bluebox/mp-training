public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private char status; // 'A' or 'I'
    private char availability; // 'A' or 'I'

    public Book() {
        this.bookId = 1;
        this.title = "sa";
        this.author = "da";
        this.category = "fa";
        this.status = 'A';
        this.availability = 'A';
    }

    public Book(int bookId, String title, String author, String category, char status, char availability) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }

    // Getters and Setters
    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public char getStatus() { return status; }
    public void setStatus(char status) { this.status = status; }
    public char getAvailability() { return availability; }
    public void setAvailability(char availability) { this.availability = availability; }

    @Override
    public String toString() {
        return "Book [bookId=" + bookId + ", title=" + title + ", author=" + author +
                ", category=" + category + ", status=" + status + ", availability=" + availability + "]";
    }
}