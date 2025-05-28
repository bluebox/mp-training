package abstractexamples;

public class Dairy extends ProductForSale {
	String name;
	public Dairy(String name,double price) {
		super("Diary", price, "Natural");
		this.name=name;
		// TODO Auto-generated constructor stub
		
	}

	@Override
	public void showDetails() {
		// TODO Auto-generated method stub
		System.out.println(name+" is of Type " +type+"\ndescription:- "+description);
		
	}

}
