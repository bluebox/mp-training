package domain;


import java.time.LocalDateTime;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private Category category;
    private Status status;
    private AvailabilityStatus availability;
    private String addedBy;
    private LocalDateTime OperationDate;

    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Book() {}

    

    public int getBookId() { return bookId; }
    public void setBookId(int bookId) { this.bookId = bookId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    public AvailabilityStatus getAvailability() { return availability; }
    public void setAvailability(AvailabilityStatus availability) { this.availability = availability; }

    public LocalDateTime getOperationDate() { return OperationDate; }
    public void setOperationDate(LocalDateTime OperationDate) { this.OperationDate = OperationDate; }

}
