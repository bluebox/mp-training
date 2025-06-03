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
		
		System.out.println("Name of the Trainer :: "+super.getName());
        System.out.println("Age of the Trainer :: "+super.getAge());
        System.out.println("Gender of the Trainer :: "+super.getGender());
        System.out.println("Address of the Trainer :: "+super.getAddress());
        System.out.println("Years of Experience :: "+this.getYearsOfExperience());
        System.out.println("Subscription details :: "+this.getSubscription());
		
	}
	
	
	
	

}
