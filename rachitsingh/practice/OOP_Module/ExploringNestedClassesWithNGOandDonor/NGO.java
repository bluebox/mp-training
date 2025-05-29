package ExploringNestedClassesWithNGOandDonor;

import java.util.ArrayList;
import java.util.List;

public class NGO {
	private String ngoName;
	private List<Donor> donorList = new ArrayList<Donor>();
	
	public NGO(String ngoName)
	{
		this.ngoName = ngoName;
	}
	
	public void insertNewDonor(String name, String email)
	{
		Donor newDonor = new Donor(name, email);
		donorList.add(newDonor);
	}
	public List<Donor> getDonors()
	{
		return this.donorList;
	}
	public void displayAllDonors()
	{
		for(int i = 0; i<donorList.size(); i++)
		{
			donorList.get(i).displayDonorDetails();
		}
	}
	
	// Creating an interface for anonymous inner class to be later implemented inside displayDonorDetails()
	interface Notify
	{
		void sendNotification(String message);
	}

		// PHASE-1 : Implementing non-static inner class
	
				// Non-static inner class Donor, can be accessed by the instance of NGO (enclosing class)
				public class Donor
				{
					private String name;
					private String email;
					private List<Donation> listOfDonations = new ArrayList<Donation>();
					
					public Donor(String name, String email)
					{
						this.name = name;
						this.email = email;
					}
					
					public List<Donation> getDonations()
					{
						return this.listOfDonations;
					}
					public void insertNewDonation(String type, int quantity)
					{
						Donation donation = new Donation(type, quantity);
						listOfDonations.add(donation);
						System.out.println("Donation by " + name + " has been received !");
					}
					public void displayDonorDetails()
					{
						System.out.println("Donor: " + name + " | Email: " + email);
						
						// Display the details of donations made by each donor along with donor's personal details
						
						for(int i = 0; i < listOfDonations.size(); i++)
						{
							listOfDonations.get(i).displayDonationDetails();
						}
						
											// PHASE-2 : Implementing Local Inner Class
											     //Creating FeedbackAssigner class which is local to .displayDonorDetails()  method, and can access name field of Donor class (as it is final)
											     class FeedbackAssigner
											     {
											    	 public void displayFeedback()
											    	 {
											    		 System.out.println("All donations from " + name + " have been accepted and greatly appreciated. Thank you.");
											    	 }
											     }
											     
											     FeedbackAssigner iGiveFeedback = new FeedbackAssigner();
											     iGiveFeedback.displayFeedback();
											     System.out.println();
					}
					
					
					// PHASE-3: Implementing Anonymous Inner Class

				    public void notifyDonor() 
				    {
				    // Creating an anonymous inner class inside sendNotification() method for one time use, to implement notify interface
				    	Notify notify = new Notify()
				    	{
				    		@Override
				    		public void sendNotification(String message) 
				    		{
				    			System.out.println(name + " has been notified successfully: " + message);
				                
				    		}
				        };

				        notify.sendNotification("Your donation has been accepted. Thank you very much.");
				     }
			
			
									// Non-static inner class Donations, can be accessed directly by the instance of Donor class
									public class Donation
									{
										private String type;
										private int quantity;
										
										public Donation(String type, int quantity)
										{
											this.quantity = quantity;
											this.type = type;
										}
										
										public void displayDonationDetails()
										{
											System.out.println("Donated : " + type + " | " + quantity);
										}
									}
				}
		// PHASE-4 : Implementing Static Inner Class
				// Creating static inner class NGOGoal, which provides general information, common for all NGO objects (so it does not need to depend on individual NGO instance)
				
				public static class NGOGoal
				{
					public static void displayNGOgoal()
					{
						System.out.println("We accept donations of food, clothes, stationary, books, electrical appliances.");
					}
				}
				
		
				
}
