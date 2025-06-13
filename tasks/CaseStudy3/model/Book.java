public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private char status;
    private char availability;

    public Book(String title, String author, String category, char status, char availability) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }

    public Book(int bookId, String title, String category, String author, char status, char availability) {
        this.bookId = bookId;
        this.title = title;
        this.category = category;
        this.author = author;
        this.status = status;
        this.availability = availability;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public char getStatus() {
        return status;
    }

    public char getAvailability() {
        return availability;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public void setAvailability(char availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return STR."Book{bookId=\{bookId}, title='\{title}', author='\{author}', category='\{category}', status=\{status}, availability=\{availability}}";
    }
}
