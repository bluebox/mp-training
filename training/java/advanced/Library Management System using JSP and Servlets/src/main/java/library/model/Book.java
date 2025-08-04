package library.model;

import library.model.enums.BookAvailability;
import library.model.enums.BookCategory;
import library.model.enums.BookStatus;
import java.time.LocalDateTime;

public class Book {
	private int bookId;
	private String title;
	private String author;
	private BookCategory category;
	private BookStatus status;
	private BookAvailability availability;
	private LocalDateTime createdAt;
	private String createdBy;
	private LocalDateTime updatedAt;
	private String updatedBy;

	
	public Book(String title, String author, BookCategory category, BookStatus status, BookAvailability availability) {
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.availability = availability;
	}

	public Book(int bookId, String title, String author, BookCategory category, BookStatus status,
			BookAvailability availability, LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt,
			String updatedBy) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.category = category;
		this.status = status;
		this.availability = availability;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
		this.updatedAt = updatedAt;
		this.updatedBy = updatedBy;
	}

	public Book() {
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

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public BookStatus getStatus() {
		return status;
	}

	public void setStatus(BookStatus status) {
		this.status = status;
	}

	public BookAvailability getAvailability() {
		return availability;
	}

	public void setAvailability(BookAvailability availability) {
		this.availability = availability;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	@Override
	public String toString() {
		return "Book{" + "bookId=" + bookId + ", title='" + title + '\'' + ", author='" + author + '\'' + ", category="
				+ category.getDisplayName() + ", status=" + status.getCode() + ", availability="
				+ availability.getCode() + ", createdAt=" + createdAt + ", createdBy='" + createdBy + '\''
				+ ", updatedAt=" + updatedAt + ", updatedBy='" + updatedBy + '\'' + '}';
	}
}