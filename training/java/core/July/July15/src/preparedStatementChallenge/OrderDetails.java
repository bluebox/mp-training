package preparedStatementChallenge;

public class OrderDetails {

	private String description;
	private int quantity;

	public OrderDetails(String description, int quantity) {

		this.description = description;
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public int getQuantity() {
		return quantity;
	}

}
