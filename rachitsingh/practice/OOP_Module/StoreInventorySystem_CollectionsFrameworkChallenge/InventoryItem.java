package StoreInventorySystem_CollectionsFrameworkChallenge_In_Progress;

public class InventoryItem {
	private Product product;
	private Integer qtyTotal;
	private Integer qtyReserved;
	private Integer qtyLow;
	private Integer qtyReorder;
	private Double salesPrice;

	public InventoryItem(Product product, Integer qtyTotal, Integer qtyReserved, Integer qtyLow, Integer qtyReorder,
			Double salesPrice) {
		this.product = product;
		this.qtyLow = qtyLow;
		this.qtyReorder = qtyReorder;
		this.qtyReserved = qtyReserved;
		this.qtyTotal = qtyTotal;
		this.salesPrice = salesPrice;
	}

	public Product getProduct() {
		return product;
	}

	public Integer getQtyLow() {
		return qtyLow;
	}
	public Integer getQtyTotal() {
		return qtyTotal;
	}

	public boolean reserveItem(Integer quantityToBeReserved) {
		if (quantityToBeReserved <= (qtyTotal - qtyReserved)) {
			qtyReserved += quantityToBeReserved;
			return true;
		}
		return false;
	}

	public boolean releaseItem(Integer quantityToBeReleased) {
		if (quantityToBeReleased <= qtyReserved) {
			qtyReserved -= quantityToBeReleased;
			return true;
		}
		return false;
	}

	public boolean sellItem(Integer quantityToBeSold) {
		if (quantityToBeSold <= qtyReserved) {
			qtyReserved -= quantityToBeSold;
			qtyTotal -= quantityToBeSold;
			checkReorder();
			return true;
		}
		return false;
	}

	public void checkReorder() {
		if (qtyTotal <= qtyLow) {
			placeInventoryOrder();
		}
	}

	public void placeInventoryOrder() {
		qtyTotal += qtyReorder;
		System.out.println("Placed the order for (reorder) " + qtyReorder + "units of " + product.getProductName());
	}

	public Double getSalesPrice() {
		return salesPrice;
	}

}
