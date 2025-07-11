package updatedbookselling.bookcatalog.domain;

public class OrderHistory {
	 private Integer orderId;
	 private Integer bookId;
	 private Integer quantity;
	 private Double originalPrice;
	 private Double finalPrice;
	 
	 public OrderHistory() {}
	 
	public OrderHistory(int orderId, int bookId, int quantity, double originalPrice, double finalPrice) {
		this.orderId = orderId;
		this.bookId = bookId;
		this.quantity = quantity;
		this.originalPrice = originalPrice;
		this.finalPrice = finalPrice;
	}
	
	
	public OrderHistory(int bookId, int quantity, double originalPrice, double finalPrice) {
		this.bookId = bookId;
		this.quantity = quantity;
		this.originalPrice = originalPrice;
		this.finalPrice = finalPrice;
	}


	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getOriginalPrice() {
		return originalPrice;
	}
	public void setOriginalPrice(double originalPrice) {
		this.originalPrice = originalPrice;
	}
	public double getFinalPrice() {
		return finalPrice;
	}
	public void setFinalPrice(double finalPrice) {
		this.finalPrice = finalPrice;
	}
	 
	 

	    // Getters and Setters


}
