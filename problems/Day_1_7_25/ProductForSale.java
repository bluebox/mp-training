package Day_1_7_25;

public abstract class ProductForSale {
   private String type;
   private double price;
   private String description;
   
   
public ProductForSale(String type, double price, String description) {
	this.type = type;
	this.price = price;
	this.description = description;
}



public String getType() {
	return type;
}
public void setType(String type) {
	this.type = type;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
   
   public void printPricedItem(int qty) {
	   System.out.println(qty+"type"+type+"price"+price+"description"+description);
   }
   
   public double getSalesPrice(int qty) {
	   return qty*price;
   }
   
   abstract void showDetails();
   
   
}
