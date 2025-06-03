package oops.Abstraction;

public abstract class ProductForSale {
   public String type;
   public double price;
   public String description;
   ProductForSale(String type,double price,String desc){
	   this.description=desc;
	   this.type=type;
	   this.price=price;
   }
   public void printPricedItem(int qty) {
	   System.out.println("price for "+qty+"is "+getSalesPrices(qty));
   }
   public double getSalesPrices(int qty) {
	   return qty*price;
   }
   abstract void showDetaisl();
}