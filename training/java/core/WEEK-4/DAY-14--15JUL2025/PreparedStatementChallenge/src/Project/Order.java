package Project;

import java.time.LocalDate;
import java.util.List;

public class Order {
	private LocalDate date;
	private List<OrderDetails> orderDetails;
	
	public Order(LocalDate date, List<OrderDetails> orderDetails) {
		this.date = date;
		this.orderDetails = orderDetails;
	}

	public LocalDate getDate() {
		return date;
	}

	public List<OrderDetails> getOrderDetails() {
		return orderDetails;
	}
	
	
	
	
}
