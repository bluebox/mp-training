package abstractexamples;

public class Dairy extends ProductForSale {
	String name;
	public Dairy(String name,double price) {
		super("Diary", price, "Natural");
		this.name=name;
		
	}

	@Override
	public void showDetails() {
		System.out.println(name+" is of Type " +type+"\ndescription:- "+description);
		
	}

}
