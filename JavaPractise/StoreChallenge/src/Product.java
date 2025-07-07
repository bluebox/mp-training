public class Product {
	private String name;
	private int id;
	private double price;
	public Product(int id ,String name,double price) {
		this.name = name;
		this.id = id;
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public int getId() {
		return id;
	}
	public double getPrice() {
		return price;
	}
	public String toString() {
		return "name=" + name + ", id=" + id + ", price=" + price ;
	}
	
	
	
	
}