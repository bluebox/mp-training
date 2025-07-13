package ExecutorServiceChallenge;

public class Order {
	
	private long orderId;
	private String item;
	private int qty;
	
	public Order(long orderId, String item, int qty) {
		this.orderId = orderId;
		this.item = item;
		this.qty = qty;
	}

	public long getOrderId() {
		return orderId;
	}

	public String getItem() {
		return item;
	}

	public int getQty() {
		return qty;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", item=" + item + ", qty=" + qty + "]";
	}
	
	
}
