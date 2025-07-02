package AbstractChallenge;

public class classB extends ProductForSale{
	private String itemB;
	  public classB(String type,double price,String discription,String itemB) 
	  {
		  super(type,price,discription);
		  this.itemB=itemB;
		  
	  }
	  public  void showDetails()
	  {
		  System.out.println("type: "+type);
		  System.out.println("price: "+price);
		  System.out.println("discription: "+discription);
		  System.out.println("itemA: "+itemB);
	  }
}
