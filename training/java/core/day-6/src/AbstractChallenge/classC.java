package AbstractChallenge;

public class classC extends ProductForSale {
	private String itemC;
	  public classC(String type,double price,String discription,String itemC) 
	  {
		  super(type,price,discription);
		  this.itemC=itemC;
		  
	  }
	  public  void showDetails()
	  {
		  System.out.println("type: "+type);
		  System.out.println("price: "+price);
		  System.out.println("discription: "+discription);
		  System.out.println("itemC: "+itemC);
	  }
}
