
public class A extends ProductForSale {
	A(String type,double price,String description)
	{
		super(type,price,description);
	}
	public void showDetails()
	{
		System.out.printf(" $%8.2feach,%-15s %-35s%n",price,type,description);
	}
}

