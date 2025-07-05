
public class B extends ProductForSale
{
		B(String type,double price,String description)
		{
			super(type,price,description);
		}
		public void showDetails()
		{
			System.out.printf("$%6.2f price %.2f%n %s%n",price,type,description);
			}
	}

