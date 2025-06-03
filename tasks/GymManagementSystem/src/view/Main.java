package view;
import java.util.Scanner;

import controller.GymController;
import model.Member;
import model.Trainer;
import view.*;


public class Main {
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	}  
	
	public static void displaySubscriptions(GymController controller)
	{
		System.out.println("Available Subscriptions :: ");
		
		controller.getSubscriptionList();
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GymController controller = new GymController();
		int condition1 = 0;
		do {
			int condition2 = 0;
			System.out.println("Gym Console");
			System.out.println("Enter 1 for Member login");
			System.out.println("Enter 2 for Trainer login");
			System.out.println("Enter 3 for Gym manager login");
			System.out.println("Enter 4 to Quit login console");
			System.out.println("Enter your option :: ");
			int menuOpt = sc.nextInt();
			sc.nextLine();
			
			switch(menuOpt)
			{
				case 1 ->{
					int condition3 = 0;
					do {
						clearScreen();
						System.out.println("Enter 1 to get Subscription details");
						System.out.println("Enter 2 to Register as a members");
						System.out.println("Enter 3 to Subscribe to a plan");
						System.out.println("Enter 4 to unSubscribe from a plan");
						System.out.println("Enter 5 to quit console ");
						System.out.println("Enter your option :: ");
						int memberOpt = sc.nextInt();
						sc.nextLine();
						switch(memberOpt)
						{
							case 1->{
								
								displaySubscriptions(controller);
							}
							
							case 2->
							{
								System.out.println("User Registration.");
								controller.createUser();
								
							}
							
							case 3->{
								displaySubscriptions(controller);
								System.out.println("Enter your name and age to fetch details... ");
								System.out.println("Enter your name :: ");
								
								String name = sc.nextLine();
								
								System.out.println("Enter your age :: ");
								
								int age = sc.nextInt();
								sc.nextLine();
								displaySubscriptions(controller);
								System.out.println("Enter the name of the Subscription which you want to subscribe");
								String subscriptionName = sc.nextLine();
								controller.makeSubscription(name, age, subscriptionName);
								
							}
							case 4->{
								System.out.println("Enter your name and age to fetch details... ");
								System.out.println("Enter your name :: ");
								
								String name = sc.nextLine();
								
								System.out.println("Enter your age :: ");
								
								int age = sc.nextInt();
								sc.nextLine();
								
								controller.deleteSubscription(name, age);
								
							}
							case 5->{
								condition3 = 1;
								break;
							}default->{
								System.out.println("Wrong option Entered...");
							}
						}
					}while(condition3 != 1);
				}
					
					case 2->{
						int condition4 = 0;
						
						clearScreen();
						System.out.println("Trainer Console");
						do {
							System.out.println("Enter 1 for list of Subscriptions");
							System.out.println("Enter 2 to know the assaigned subscription and members");
//							System.out.println("Enter 3 to Quit the job");
							System.out.println("Enter 3 to Quit");
							int op = sc.nextInt();
							sc.nextLine();
							switch(op)
							{
								case 1->
								{
									displaySubscriptions(controller);
								}
								case 2->
								{
									try {
										System.out.println("Enter Your name age to fetch the details");
										System.out.println("Enter Your name :: ");
										String name = sc.nextLine();
										if(name == "")
										{
											System.out.println("Got none as input please try again");
											continue;
										}
										System.out.println("Enter your age :: ");
										int age = sc.nextInt();
										sc.nextLine();
										if(age<=0 || age>=100)
										{
											System.out.println("Got invalid age as input please try again");
											continue;
										}
										
										Trainer trainer = controller.getTrainerDetails(name,age);
										
										if(trainer == null)
										{
											System.out.println("Trainer Not Found!!\nPlease Try again");
											continue;
										}
										
										System.out.println("Enter 1 to know about the Subscription assaigned");
										System.out.println("Enter 2 to know about the Members associated");
										System.out.println("Enter your option :: ");
										int option = sc.nextInt();
										sc.nextLine();
										if(option == 1)
										{
											controller.performTrainerAction(trainer, t -> {
										        System.out.println("Trainer: " + t.getName());
										        for (model.Subscription s : controller.getAllSubscriptions()) {
										            if (s.getTrainer().equals(t)) {
										                System.out.println("Assigned Subscription: " + s.getSubscriptionName());
										                break;
										            }
										        }
										    });
										}else if(option == 2)
										{
											controller.performTrainerAction(trainer, t -> {
										        for (model.Subscription s : controller.getAllSubscriptions()) {
										            if (s.getTrainer().equals(t)) {
										                System.out.println("Members under subscription: " + s.getSubscriptionName());
										                for (model.Member m : controller.getAllMembers()) {
										                    if (m.getSubscribe() != null && m.getSubscribe().equals(s)) {
										                        System.out.println("- " + m.getName() + ", Age: " + m.getAge());
										                    }
										                }
										                break;
										            }
										        }
										    });
										}else
										{
											System.out.println("Received unknown command...");
										}
									}catch(NumberFormatException nfe)
									{
										System.out.println("Wrong input received. Please try again...");
									}
								}
								case 3->{
									condition4 = 1;
									break;
								}
								default->{
									System.out.println("Received unknown command...");
								}
							}
							
							
						}while(condition4!=1);
						
					}
					case 3->{
						int condition5 = 0;
						do {
							System.out.println("Manager Console :: ");
							System.out.println("Enter 1 to display Trainers and Their Subscriptions ");
							System.out.println("Enter 2 to display Members and Their Subscriptions ");
							System.out.println("Enter 3 to add new Subscription");
							System.out.println("Enter 4 to add trainer to a subscription");
							System.out.println("Enter 5 to create new Trainer");
							System.out.println("Enter 6 to quit");
							System.out.println("Enter your option :: ");
							int option = sc.nextInt();
							sc.nextLine();
							if(option == 1)
							{
								controller.displayTrainersDetails();
							}else if(option == 2)
							{
								controller.displayMembersDetails();
							}else if(option == 3)
							{
								System.out.println("Enter the name of the subscription :: ");
								String subscriptionName = sc.nextLine();
								
								
								System.out.println("Enter the amount of the subscription :: ");
								int amount = sc.nextInt();
								sc.nextLine();
								
								
								System.out.println("Enter the duration of the subscription :: ");
								double duration = sc.nextDouble();
								sc.nextLine();
								
								System.out.println("Enter the Details of the Subscription :: ");
								String discription = sc.nextLine();
								
//								System.out.println("")
								
								controller.createNewSubscription(subscriptionName,amount,duration,discription,null);
								System.out.println("Subscription created. Add the trainer from the menu");
							}else if(option == 4)
							{
								System.out.println("List of subscription with out trainer");
								controller.assignTrainer();
							}else if(option == 5)
							{
								System.out.println("Enter Trainers details...");
								System.out.println("Enter Trainers Name :: ");
								String name = sc.nextLine();
								
								System.out.println("Enter Trainers age :: ");
								int age = sc.nextInt();
								sc.nextLine();
								
								System.out.println("Enter Trainers gender :: ");
								String gender = sc.nextLine();
								
								System.out.println("Enter Trainers address :: ");
								String address = sc.nextLine();
								
								System.out.println("Enter Trainers Years Of experience in training :: ");
								int YoE = sc.nextInt();
								sc.nextLine();
								
								controller.createNewTrainer(name, age, gender, address, YoE);
							}else if(option == 6)
							{
								condition5 = 1;
								break;
							}else
							{
								System.out.println("Received unknown command...");
							}
						}while(condition5!= 1);
					}
					case 4->{
						condition1 = 1;
						break;
					}
					default ->
					{
						System.out.println("Received unknown command...");
					}
				}
			
		}while(condition1 != 1);
	}
}
