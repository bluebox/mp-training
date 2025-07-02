package AbstractChallenge;

abstract class ProductForSale {
 protected String type;
 protected double price;
 protected String discription;
  public ProductForSale(String type,double price,String discription)
  {
	  this.type=type;
	  this.price=price;
	  this.discription=discription;
	  
  }
  public double getSalesPrice(int qty)
  {
	return qty*price;  
  }
  public void printPricedItem(int qty)
  {
	  System.out.println(type+" : "+qty+" items of each "+price+" total :"+getSalesPrice(qty));
  }
 public abstract void showDetails();
// public String getType()
// {
//	 return type;
// }
// public  double getPrice()
// {
//	 return price;
// }
// public String getDiscription()
// {
//	 return discription;
// }
}
