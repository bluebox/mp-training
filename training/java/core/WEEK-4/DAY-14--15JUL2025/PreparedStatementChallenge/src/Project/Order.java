package Project;

import java.time.LocalDate;
import java.util.List;

public class Order {
	private LocalDate date;
	private OrderDetails orderDetails;
	
	public Order(LocalDate date, OrderDetails orderDetails) {
		this.date = date;
		this.orderDetails = orderDetails;
	}

	public LocalDate getDate() {
		return date;
	}

	public OrderDetails getOrderDetails() {
		return orderDetails;
	}
	
	
	
	
}
