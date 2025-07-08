package Store;

public class InventoryItem {
   private Product product;
   private int totalQty;
   private int qtyReserved;//the quantity in the carts
   private int qtyReorder;//the reorder quantity while placing order
   private int qtyLow;//trigger to place order
   private double salesPrice;// price tag
  
  public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
	public int getQtyReserved() {
		return qtyReserved;
	}
	public void setQtyReserved(int qty) {
		this.qtyReserved = qty;
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
	
	public void reserveItem(int qty) {
		setQtyReserved(qty);
	}
    
	public void releaseItem() {
		setQtyReserved(getQtyReserved()-1);
	}
	
	public void sellItem(double value) {
		setTotalQty(getTotalQty()-1);
		setSalesPrice(value);
	}
	
	public void placeInventoryOrder() {
		setQtyReorder(1000);
	}
    
    
}
