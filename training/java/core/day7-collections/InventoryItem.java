package day7;

public class InventoryItem {
	private Product product;
	private int qtyTotal;
	private int qtyReserved;
	private int qtyReorder;
	private int qtyLow;
	private int salesPrice;
	public InventoryItem(Product product, int qtyTotal, int qtyReorder, int qtyLow, int salesPrice) {
		this.product = product;
		this.qtyTotal = qtyTotal;
		this.qtyReserved = 0;
		this.qtyReorder = qtyReorder;
		this.qtyLow = qtyLow;
		this.salesPrice = salesPrice;
		placeInventoryOrder();
	}
	@Override
	public String toString() {
		return "InventoryItem [product=" + product + ", salesPrice=" + salesPrice + "]";
	}
	public boolean reserveItem(int quantity) {
		if(qtyTotal-qtyReserved>=quantity) {
			qtyReserved+=quantity;
			return true;
		}
		return false;
		
	}
	
	public void releaseItem(int quantity) {
		if(qtyTotal<=qtyLow) { 
			qtyTotal+=qtyReorder;
		}
	}
	
	public void sellItem() {
		if(qtyReserved<=0) {
			System.out.println("no items are reserved to sell");
			return;
		}
		qtyReserved--;
		qtyTotal--;
		placeInventoryOrder();
	}
	public void placeInventoryOrder() {
		if(qtyTotal<=qtyLow) {
			qtyTotal+=qtyReorder;
		}
	}
}
