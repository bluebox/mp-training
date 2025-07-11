package day11;

public class Order {
	private final int orderId;
	private final String shoeType;

	private final int quantity;

	public Order(int orderId, String shoeType, int quantity) {
		super();
		this.orderId = orderId;
		this.shoeType = shoeType;
		this.quantity = quantity;
	}

	public int getOrderId() {
		return orderId;
	}

	public String getShoeType() {
		return shoeType;
	}

	public int getQuantity() {
		return quantity;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", shoeType=" + shoeType + ", quantity=" + quantity + "]";
	}
}
