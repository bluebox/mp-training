package model;

public class Trainer extends Person{
	
	
	private String trainerId;
	private int yearsOfExperience;

	public Trainer(String name, int age, String gender, String address,String trainerId, int yearsOfExperience) {
		super(name, age, gender, address);
		this.yearsOfExperience = yearsOfExperience;
		this.trainerId = trainerId;
	}

	
	
	
	
	
	/* 
	 * Needs implementation for this class....
	 * */
	@Override
	public void getPersonalDetails() {
		
		super.toString();
		
	}
	
	
	
	

}
