package pojo;

public class Book {
	public enum Status {
        ACTIVE,
        INACTIVE
    }
    public enum Availability {
        AVAILABLE,
        ISSUED
    }
	private int bookid;
    private String title;
    private String author;
    private String category;
    private Status status;
    private Availability availability;
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
	@Override
	public String toString() {
		return "Book [bookid=" + bookid + ", title=" + title + ", author=" + author + ", category=" + category
				+ ", status=" + status + ", availability=" + availability + "]";
	}
}
