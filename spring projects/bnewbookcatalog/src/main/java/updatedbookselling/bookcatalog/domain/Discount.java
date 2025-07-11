package updatedbookselling.bookcatalog.domain;

public class Discount {
	
	private Integer id;
    private Double price;
    private Double discount;
    
    public Discount() {}
    
	public Discount(int id, double price, double discount) {
		this.id = id;
		this.price = price;
		this.discount = discount;
	}

	public Discount(double price, double discount) {
		this.price = price;
		this.discount = discount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
    
    

    // Getters and Setters	
	

}
