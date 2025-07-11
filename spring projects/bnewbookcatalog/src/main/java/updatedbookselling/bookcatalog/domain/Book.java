package updatedbookselling.bookcatalog.domain;


public class Book {
    private Integer bookId;
    private String title;
    private Double cost;
    private Integer quantity;
	private Double discount;
	private Integer wishListedCount=0;
	private Integer soldCount=0;
    
    

	public Book() {}

	public Book(int bookId, String title, double cost, int quantity, double discount) {
		this.bookId = bookId;
		this.title = title;
		this.cost = cost;
		this.quantity = quantity;
		this.discount = discount;
	}

	public Book(String title, double cost, int quantity, double discount) {
		this.title = title;
		this.cost = cost;
		this.quantity = quantity;
		this.discount = discount;
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

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	//---
	
	public double getwishListedCount() {
		return wishListedCount;
	}

	public void setwishListedCount(Integer count) {
		this.wishListedCount = count;
	}
    
    
	public Integer getSoldCount() {
		return soldCount;
	}

	public void setSoldCount(Integer soldCount) {
		this.soldCount = soldCount;
	}
	
}

