package AbstractExamples;


public class Electronics extends ProductForSale {
	String name;
	
	public Electronics (String name,double price) {
		super("Electronics", price, "Manufactured ");
		this.name=name;
		
	}

	@Override
	public void showDetails() {
		System.out.println(name+" is of Type " +type+"\ndescription:- "+description);
		
	}

}