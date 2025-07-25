package domain;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private BookStatus status;               // A / I
    private AvailabilityStatus availability; // A / I
    
	public Book(int bookId, String title, String author, String category, BookStatus status,
			AvailabilityStatus availability) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.availability = availability;
	}
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public BookStatus getStatus() {
		return status;
	}
	public void setStatus(BookStatus status) {
		this.status = status;
	}
	public AvailabilityStatus getAvailability() {
		return availability;
	}
	public void setAvailability(AvailabilityStatus availability) {
		this.availability = availability;
	}

    

   
}

