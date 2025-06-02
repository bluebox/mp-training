package model;

public class Trainer extends Person{
	
	
	private Subscription subscription;
	private int yearsOfExperience;

	public Trainer(String name, int age, String gender, String address, int yearsOfExperience,Subscription subscription) {
		super(name, age, gender, address);
		this.yearsOfExperience = yearsOfExperience;
		this.subscription = subscription;
	}

	
	
	
	
	
	public Subscription getSubscription() {
		return subscription;
	}






	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}






	public int getYearsOfExperience() {
		return yearsOfExperience;
	}






	public void setYearsOfExperience(int yearsOfExperience) {
		this.yearsOfExperience = yearsOfExperience;
	}






	/* 
	 * Needs implementation for this class....
	 * */
	@Override
	public void getPersonalDetails() {
		
		super.toString();
		
	}
	
	
	
	

}
