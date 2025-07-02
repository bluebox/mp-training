package AbstractChallenge;

public class classA  extends ProductForSale{
  private String itemA;
  public classA(String type,double price,String discription,String itemA) 
  {
	  super(type,price,discription);
	  this.itemA=itemA;
	  
  }
  public  void showDetails()
  {
	  System.out.println("type: "+type);
	  System.out.println("price: "+price);
	  System.out.println("discription: "+discription);
	  System.out.println("itemA: "+itemA);
  }
}
