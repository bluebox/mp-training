package Orm;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	int order_id;
	String customer_name;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	public List<OrderItem> items = new ArrayList<>();

	public Order() {
	};

	public Order(String name) {
		this.customer_name = name;
	}

	public String getName() {
		return customer_name;
	}

	public void setName(String customer_name) {
		this.customer_name = customer_name;
	}

	public void addOrderItem(OrderItem orderItem) {
		items.add(orderItem);
	}

	public List<OrderItem> getItems() {
		return this.items;
	}
}
