package oops.abstraction;

public abstract class Company {
	protected String companyName;
	protected String locationOfCompany;
	
	public Company(String companyName,String locationOfCompany){
		this.companyName=companyName;
		this.locationOfCompany=locationOfCompany;
	}
		
	public abstract void showData();
}
