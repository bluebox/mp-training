package updatedbookselling.bookcatalog.domain;

import java.sql.Date;

public class Orders {
	private Integer orderId;
    private Integer memberId;
    private Integer totalBooks;
    private Double booksDiscount;
    private Double slabDiscount;
    private Double originalPrice;
    private Double finalPrice;
    private Date purchaseDate;
    
    public Orders() {}
    
	public Orders(int orderId, int memberId, int totalBooks, double booksDiscount, double slabDiscount,
			double originalPrice, double finalPrice, Date purchaseDate) {
		this.orderId = orderId;
		this.memberId = memberId;
		this.totalBooks = totalBooks;
		this.booksDiscount = booksDiscount;
		this.slabDiscount = slabDiscount;
		this.originalPrice = originalPrice;
		this.finalPrice = finalPrice;
		this.purchaseDate = purchaseDate;
	}


	public Orders(int memberId, int totalBooks, double booksDiscount, double slabDiscount, double originalPrice,
			double finalPrice, Date purchaseDate) {
		this.memberId = memberId;
		this.totalBooks = totalBooks;
		this.booksDiscount = booksDiscount;
		this.slabDiscount = slabDiscount;
		this.originalPrice = originalPrice;
		this.finalPrice = finalPrice;
		this.purchaseDate = purchaseDate;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getMemberId() {
		return memberId;
	}


	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}


	public int getTotalBooks() {
		return totalBooks;
	}


	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}


	public double getBooksDiscount() {
		return booksDiscount;
	}


	public void setBooksDiscount(double booksDiscount) {
		this.booksDiscount = booksDiscount;
	}


	public double getSlabDiscount() {
		return slabDiscount;
	}


	public void setSlabDiscount(double slabDiscount) {
		this.slabDiscount = slabDiscount;
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


	public java.sql.Date getPurchaseDate() {
		return purchaseDate;
	}


	public void setPurchaseDate(java.sql.Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
    

}
