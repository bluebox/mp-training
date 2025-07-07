package storeInventorySystem;

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
	
	public boolean reserveItem(int qty) {
        if (qty + qtyReserved <= (qtyTotal )) {
            qtyReserved += qty;
            return true;
        }
        return false;
    }

    public boolean releaseItem(int qty) {
        if (qty <= qtyReserved) {
            qtyReserved -= qty;
            return true;
        }
        return false;
    }

    public boolean sellItem(int qty) {
        if (qty <= qtyReserved) {
            qtyReserved -= qty;
            qtyTotal -= qty;
            return true;
        }
        return false;
    }

    public boolean placeInventoryOrder() {
        if (qtyTotal <= qtyLow) {
            qtyTotal += qtyReorder;
            return true;
        }
        return false;
    }

	@Override
	public String toString() {
		return "InventoryItem [prodcut=" + product + ", qtyTotal=" + qtyTotal + ", qtyReserved=" + qtyReserved
				+ ", qtyReorder=" + qtyReorder + ", qtyLow=" + qtyLow + ", salesPrice=" + salesPrice + "]";
	}

	public Product getProduct() {
		return product;
	}

	public int getQtyTotal() {
		return qtyTotal;
	}

	public int getQtyReserved() {
		return qtyReserved;
	}

	public int getQtyReorder() {
		return qtyReorder;
	}

	public int getQtyLow() {
		return qtyLow;
	}

	public double getSalesPrice() {
		return salesPrice;
	}
 
    
}
