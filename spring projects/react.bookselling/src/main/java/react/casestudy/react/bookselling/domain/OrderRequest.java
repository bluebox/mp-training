package react.casestudy.react.bookselling.domain;

import java.util.List;

public class OrderRequest {
    private Orders order;
    private List<OrderHistory> items;
    
    public OrderRequest() {}
    
    public OrderRequest(Orders order, List<OrderHistory> items) {
		
		this.order = order;
		this.items = items;
	}
	// Getters and Setters
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public List<OrderHistory> getItems() {
		return items;
	}
	public void setItems(List<OrderHistory> items) {
		this.items = items;
	}
}
