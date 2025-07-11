package updatedbookselling.bookcatalog.domain;

public class WishList {
	private Integer wishlistId;
    private Integer memberId;
    private Integer bookId;
    
    public WishList() {}
    
	public WishList(int wishlistId, int memberId, int bookId) {
		super();
		this.wishlistId = wishlistId;
		this.memberId = memberId;
		this.bookId = bookId;
	}

	public WishList(int memberId, int bookId) {
		super();
		this.memberId = memberId;
		this.bookId = bookId;
	}

	public int getWishlistId() {
		return wishlistId;
	}

	public void setWishlistId(int wishlistId) {
		this.wishlistId = wishlistId;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}
    

    // Getters and Setters
	
	


}
