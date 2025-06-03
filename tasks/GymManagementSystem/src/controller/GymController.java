package controller;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;




public class GymController {
	
	ArrayList<Member> members;
	
	ArrayList<Trainer> trainers;
	
	ArrayList<Subscription>subscriptions;
	
	public GymController()
	{
		members = new ArrayList<>();
		subscriptions = new ArrayList<>();
		trainers = new ArrayList<>();
		subscriptions.add(new Subscription("Yearly",1000,12,"Yearly Subscription for gym",null));
		members.add(new Member("Madhav",21,"Male","bagh-amberpet",null));
		trainers.add(new Trainer("Ravi", 28, "male", "KPHB", 5, null));
		members.get(0).setStartDate(LocalDate.of(2025, 2, 1));
		trainers.get(0).setSubscription(subscriptions.get(0));
		members.get(0).setSubscribe(subscriptions.get(0));
		subscriptions.get(0).setTrainer(trainers.get(0));
	}
	
	
	
	public ArrayList<Subscription> getAllSubscriptions() {
	    return subscriptions;
	}

	public ArrayList<Member> getAllMembers() {
	    return members;
	}
	
	public void getSubscriptionList()
	{
		subscriptions.forEach((s)->{
			System.out.println("");
			System.out.println("Name of the subscription :: "+s.getSubscriptionName());
			System.out.println("Trainer :: " + (s.getTrainer() != null ? s.getTrainer().getName() : "—"));
			System.out.println("Amount :: "+s.getAmount());
			System.out.println("Duration :: "+s.getDuration());
			System.out.println("Additional Details :: "+s.getDetails());
			
		});
	}
	
	
	public boolean isMemberPresent(String name, int age)
	{
		for(var member: members)
		{
			if((member.getName().equals(name)) && (member.getAge() == age))
			{
				return true;
			}
		}
		return false;
	}
	
	public Member createUser()
	{
		String name,gender,address;
		int age;
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Details :: ");
		System.out.println("Member Name :: ");
		name = sc.nextLine();
		System.out.println("Member age :: ");
		age = sc.nextInt();
		sc.nextLine();
		System.out.println("Member gender :: ");
		gender = sc.nextLine();
		System.out.println("Member address :: ");
		address = sc.nextLine();
		
		Member newMember = new Member(name,age,gender,address,null);
		
		members.add(newMember);
		newMember.getPersonalDetails();
		return newMember;
		
	}
	
	
	public void deleteSubscription(String name,int age)
	{
		Member getMember = null;
		for(var member: members)
		{
			if((member.getName().equals(name)) && (member.getAge() == age))
			{
				getMember = member;
				break;
			}
		}
		if(getMember == null)
		{
			System.out.println("User not found...");
			return;
		}
		try {
			Subscription currentSubscription = getMember.getSubscribe();
			
			int usedAmount = calculateUsedAmount(getMember);
			
			int returnAmount = usedAmount==currentSubscription.getAmount()?0:currentSubscription.getAmount()-usedAmount;
			getMember.setSubscribe(null);
			System.out.println("Return amount from the cancled subscription is :: "+returnAmount);
			System.out.println("Subscription has been cancled");
		}catch(NullPointerException npe)
		{
			System.out.println("The Member has subscribed to no subscription");
		}
		
		
	}
	
	
	public static int calculateUsedAmount(Member member)
	{
		if (member.getSubscribe() == null) 
		{
			return 0;
		}
		Subscription subscription = member.getSubscribe();
		double duration = subscription.getDuration();
		int ratePerMonth = (int)(subscription.getAmount()/duration);
		
		LocalDate start = member.getStartDate();
        LocalDate now = LocalDate.now();
        long monthsUsed = ChronoUnit.MONTHS.between(start, now);
        
        
        monthsUsed = (long) (monthsUsed>duration?duration:monthsUsed);
        
        int amountUsed = (int)(monthsUsed * ratePerMonth);
        return amountUsed;
	}
	
	public void makeSubscription(String name, int age,String subscriptionName)
	{
		try {
			
			if(subscriptions.size() ==0)
			{
				throw new NoSubscriptionsAvailableException();
			}
			Scanner sc = new Scanner(System.in);
			
			Member getMember = null;
			for(var member: members)
			{
				if((member.getName().equals(name)) && (member.getAge() == age))
				{
					getMember = member;
					break;
				}
			}
			
			if(getMember == null)
			{
				System.out.println("User not in registered users list...");
				System.out.println("Creating new user");
				getMember= createUser();
			}
			
			
			Subscription newSubscription = null;
			for(var subscription : subscriptions)
			{
				if(subscription.getSubscriptionName().equals(subscriptionName))
				{
					
					newSubscription = subscription;
					break;
				}
			}
			
			if(newSubscription == null)
			{
				System.out.println("Subscription "+subscriptionName+" not found!");
				return;
			}
			
			int credit = 0;
			if(getMember.getSubscribe() != null)
			{
				Subscription currentSubscription = getMember.getSubscribe();
				int amountUsed = calculateUsedAmount(getMember);
		        credit = currentSubscription.getAmount()- amountUsed;
		        
		        System.out.println("Credit from previous subscription = "+credit);
		        System.out.println("This amount will be transfeted for upgradation...");
		        System.out.println("Enter Yes/No(Y/N) to confirm or decline the trensaction :: ");
		        String option = sc.nextLine();
		        
		        if(option.toLowerCase().equals("n") || option.toLowerCase().equals("now") )
		        {
		        	System.out.println("Cancled Request");
		        	return;
		        }
		        else
		        {
		        	int amountToPay = newSubscription.getAmount() - credit;
		        	int returnAmount = amountToPay;
		            if (amountToPay < 0) amountToPay = 0;
	
		            
		            System.out.println("New subscription: " + newSubscription.getSubscriptionName());
		            System.out.println("Amount to pay after credit: ₹" + amountToPay);
		            System.out.println("Amount Returned :: "+(returnAmount<0?-(returnAmount):0));
		            getMember.setSubscribe(newSubscription);
		            getMember.setStartDate(LocalDate.now());
	
		            System.out.println("Subscription successfully updated!");
		        }
		        
		        
		        
		        
			}else
			{
				getMember.setSubscribe(newSubscription);
			    getMember.setStartDate(LocalDate.now());
			    System.out.println("You have been subscribed to: " + newSubscription.getSubscriptionName());
			}
			
		}catch(NoSubscriptionsAvailableException nsae)
		{
			System.out.println("No Subscriptions available at present!!\n OOPS!!!");
		}catch(Exception E)
		{
			E.printStackTrace();
		}
	}
	
	public Trainer getTrainerDetails(String name, int age)
	{
		for(var trainer:trainers)
		{
			if(trainer.getName().equals(name) && trainer.getAge() == age)
			{
				return trainer;
			}
		}
		return null;
	}
	
	
	public void performTrainerAction(Trainer trainer, TrainerAction action) {
		if(trainer == null)
		{
			System.out.println("Trainer not found!!\nPlease try again");
			return;
		}
		
		action.execute(trainer);
	}
	
	
	public void displayTrainersDetails()
	{
		System.out.println("Trainers and their assaigned details");
		for(var trainer:trainers)
		{
			System.out.println("Trainer Name :: "+trainer.getName());
			System.out.println("Assaigned to :: " + (trainer.getSubscription() != null ? trainer.getSubscription().getSubscriptionName(): "—"));
		}
	}
	
	
	public void displayMembersDetails()
	{
		System.out.println("Members and their assaigned details");
		for(var member:members)
		{
			System.out.println("Member Name :: "+member.getName());
			System.out.println("Subscribed to :: " + (member.getSubscribe() != null ? member.getSubscribe().getSubscriptionName(): "—"));

		}
	}
	
	public void createNewSubscription(String subscriptionName,int amount,double duration,String details, Trainer trainer)
	{
		Subscription newSubscription = new Subscription(subscriptionName, amount, duration, details, trainer);
		subscriptions.add(newSubscription);
	}
	
	public void assignTrainer() {
		Scanner sc = new Scanner(System.in);
	    int id = 1;
	    ArrayList<Subscription> unassigned = new ArrayList<>();

	    for (var subscription : subscriptions) {
	        if (subscription.getTrainer() == null) {
	            System.out.println("Id: " + id + ", Subscription name: " + subscription.getSubscriptionName());
	            unassigned.add(subscription);
	            id++;
	        }
	    }

	    if (unassigned.isEmpty()) {
	        System.out.println("All subscriptions already have trainers.");
	        return;
	    }

	    System.out.println("Enter the ID of the subscription you want to assign a trainer to:");
	    int selectedId = sc.nextInt();
	    sc.nextLine();

	    if (selectedId <= 0 || selectedId > unassigned.size()) {
	        System.out.println("Invalid ID selected.");
	        return;
	    }

	    Subscription selectedSub = unassigned.get(selectedId - 1);

	    System.out.println("Enter trainer name: ");
	    String trainerName = sc.nextLine();

	    System.out.println("Enter trainer age: ");
	    int age = sc.nextInt();
	    sc.nextLine();

	    
	    for (Subscription sub : subscriptions) {
	        Trainer existingTrainer = sub.getTrainer();
	        if (existingTrainer != null &&
	            existingTrainer.getName().equals(trainerName) &&
	            existingTrainer.getAge() == age) {
	            System.out.println("This trainer is already assigned to another subscription.");
	            return;
	        }
	    }

	    Trainer ourTrainer = null;
	    for(var trainer:trainers)
	    {
	    	if(trainer.getName().equals(trainerName) && trainer.getAge() == age)
	    	{
	    		ourTrainer = trainer;
	    		trainer.setSubscription(selectedSub);
	    	}
	    }
	    
	    if(ourTrainer == null)
	    {
	    	System.out.println("Trainer not found!!\nPlease Try again...");
	    	return;
	    }
	    
	    selectedSub.setTrainer(ourTrainer);
	    trainers.add(ourTrainer);

	    System.out.println("Trainer assigned successfully to " + selectedSub.getSubscriptionName());
	}

	public void createNewTrainer(String name, int age, String gender, String address, int yearsOfExperience)
	{
		Trainer trainer = getTrainerDetails(name, age);
		if(trainer == null)
		{
			trainer = new Trainer(name, age,gender, address,yearsOfExperience,null);
			trainers.add(trainer);
			System.out.println("Successfully added Trainer");
			System.out.println("Details...");
			trainer.getPersonalDetails();;
			return;
		}
		
		System.out.println("Trainer with the given details already exists...");
	}
}
