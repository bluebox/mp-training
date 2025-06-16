package Pojo;

public class Book {
	private int bookId;
	private String title;
	private String author;
	private String category;
	private Status status;
	private Availability availability;

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

	public char getStatus() {
		return status.name().toUpperCase().charAt(0);
	}

	public void setStatus(char status) {
		this.status = (status == 'A' ? Status.ACTIVE : Status.INACTIVE);
	}

	public char getAvailability() {
		return availability.name().toUpperCase().charAt(0);
	}

	public void setAvailability(char availability) {
		this.availability = (availability == 'I' ? Availability.ISSUED : Availability.AVAILABLE);
	}

	public String toString() {
		return "Book{" + "bookId=" + bookId + ", title='" + title + '\'' + ", author='" + author + '\'' + ", category='"
				+ category + '\'' + ", status=" + status + ", availability=" + availability + "}";
	}

}
