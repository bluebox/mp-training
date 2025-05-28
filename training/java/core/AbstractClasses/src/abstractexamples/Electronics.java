package abstractexamples;

public class Electronics extends ProductForSale 
{
	String name;
	public Electronics(String name, double price) {
		super("Electronics", price, "High quality electronics");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println(name+" is of Type " +type+"\ndescription:- "+description);
		
		
	}
	
}

