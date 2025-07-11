public class Order {
	int id;
	String name;
	int qty;
	public Order(int id, String name, int qty) {
		super();
		this.id = id;
		this.name = name;
		this.qty = qty;
	}
	public String getName() {
		return name;
	}
	
}
