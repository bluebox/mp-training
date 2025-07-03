package July1;

public class A extends ProductForSale {
	A(String type,double price,String description)
	{
		super(type,price,description);
	}
	public void showDetails()
	{
		System.out.printf(" $%8.2f each, %-15s %-35s%n",getPrice(),getType(),getDescription());
		/*System.out.println("Type: " + getType());
        System.out.println("Price: " + getPrice());
        System.out.println("Description: " + getDescription());*/
	}
}
