package StoreInventry;

public class InventoryItem {
	private Product product;
    private int qtyTotal;
    private int qtyReserved;
    private int qtyReorder;
    private int qtyLow;
    private double salesPrice;
    
	public InventoryItem(Product product, int qtyTotal, int qtyReserved, int qtyReorder, int qtyLow,
			double salesPrice) {
		this.product = product;
		this.qtyTotal = qtyTotal;
		this.qtyReserved = qtyReserved;
		this.qtyReorder = qtyReorder;
		this.qtyLow = qtyLow;
		this.salesPrice = salesPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQtyTotal() {
		return qtyTotal;
	}

	public void setQtyTotal(int qtyTotal) {
		this.qtyTotal += qtyTotal;
	}

	public int getQtyReserved() {
		return qtyReserved;
	}

	public void setQtyReserved(int qtyReserved) {
		this.qtyReserved += qtyReserved;
	}

	public int getQtyReorder() {
		return qtyReorder;
	}

	public void setQtyReorder(int qtyReorder) {
		this.qtyReorder = qtyReorder;
	}

	public int getQtyLow() {
		return qtyLow;
	}

	public void setQtyLow(int qtyLow) {
		this.qtyLow = qtyLow;
	}

	public double getSalesPrice() {
		return salesPrice;
	}

	public void setSalesPrice(double salesPrice) {
		this.salesPrice = salesPrice;
	}
    
	public void releaseItem(int qty) {
        this.qtyReserved -= qty;
    }
	
	 public void sellItem(int qty) {
	        this.qtyTotal -= qty;
	        this.qtyReserved -= qty;
	    }
	

}
