package corejava.july1_AbsractClass;

public class Items {
	private String name;
	private String type;
	private double price;
	
	public Items(String name, String type, double price) {
		super();
		this.name = name;
		this.type = type;
		this.price = price;
	}
	
	
	public String getName() {
		return name;
	}
	public String getType() {
		return type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
