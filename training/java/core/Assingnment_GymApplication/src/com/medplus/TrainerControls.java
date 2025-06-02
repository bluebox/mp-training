package com.medplus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public abstract class TrainerControls {
	private int TrainerId = 12345;
	private static ArrayList<Member> tempmembers = Gym.getArrayListMembers();

	public TrainerControls() {
		int TrainerIdByUser = Input.getNumberInRange1(0, 9999999); 
		if (TrainerId == TrainerIdByUser) {
			trainercontrols();
		}else {
			System.out.println("Trainer not found \n Returning to Main Menu .");
		}

	}
	
	public void trainercontrols() {
		int choosingNumber;
		while(true) {
			System.out.println("""
					1.Search Member By Name
					2.Search Member By Id
					3.Create a New Member
					4.See Members
					5.Remove by Id
					6.Remove by Name
					7.Return to Main Menu
					
					""");
			
			
			choosingNumber = Input.getNumberInRange(1, 7);
			if (choosingNumber==1) {
				ArrayList<Member> listedNames = SearchByName();
				if(listedNames.size()==0) {
					System.out.println("No Match Found");
				}else {
					printMembers(listedNames);
					System.out.println("\nChoose corresponding index for a perticular Member \n else press 0");
					int temp =Input.getNumberInRange(0,listedNames.size());
					if (temp ==0) {
						System.out.println("Going to Previous Menu ");
					}else {
						operationOnMember(listedNames.get(temp-1));
					}
				}
			}else if (choosingNumber ==2) {
				SearchById();
			}else if (choosingNumber ==3) {
				addMember();
			}else if (choosingNumber ==4) {
				printMembers(tempmembers);
			}else if (choosingNumber ==5) {
				for(var i : tempmembers) {
					int removeId = Input.getNumberInRange(10000,100000);
					if (i.getMemberId() == removeId) {
						i.getPersonDetails();
						System.out.println("Are You Sure To Remove This Member \n if YES press 1 else press 2 \nEnter your input");
						if (Input.getNumberInRange(1, 2)==1) {
							tempmembers.remove(i);
							System.out.println("Succesfully Removed the Member .");
						}
						
					}
				}
			}else if (choosingNumber ==6) {
				ArrayList<Member> listedNames = SearchByName();
				if(listedNames.size()==0) {
					System.out.println("No Match Found");
				}else {
					printMembers(listedNames);
					System.out.println("Choose corresponding index for a perticular Member \n else press 0");
					int temp =Input.getNumberInRange(0,listedNames.size());
					if (temp ==0) {
						System.out.println("Going to Previous Menu ");
					}else {
						tempmembers.remove(listedNames.get(temp-1));
					}
				}
			}else {
				break;
			}
			
			
		}
	}
	
	public void addMember() {
		Random random = new Random();
		int id = random.nextInt(10000,100000);
		for (var i : tempmembers) {
			if (i.getMemberId() == id) {
				addMember();
			}
		}
		Member temp = new Member(id,Input.getValidatedName(), Input.calculateAge(Input.getValidatedDOB()), LocalDate.now(), Input.getValidatedMembershipPlan());
		tempmembers.add(temp);
		System.out.println("Sussfully added New Member");
		temp.getPersonDetails();
	}
	
	
	public ArrayList<Member> SearchByName() {
		ArrayList<Member> listBySameName = new ArrayList<>();
		String name = Input.getValidatedName();
		for (var i : tempmembers) {
			if (i.getName().equalsIgnoreCase(name)) {
				listBySameName.add(i);
			}
		}
		return listBySameName;
	}
	
	public void SearchById() {
		int id = Input.getNumberInRange(9999,100000);
		if (id != 9999) {
			for(var i : tempmembers) {
				if (i.getMemberId()==id) {
					operationOnMember(i);				}
			}
		}else if (id == 9999) {
			System.out.println("Returning to Trainer Menu ");
		}else {
			{
				System.out.println("""
						Enter 9999 to quit 
						Member Not Found Try again !!
						
						""");
				SearchById();
				
			}
		}
	}
	
	public void printMembers(ArrayList<Member> PrintingList) {
		System.out.printf("%10s %10s %10s %10s %15s %15s %s".formatted("Member Id","Name","Age","Plan","Start Date","EndDate","Status"));
		for (var i : PrintingList)
			System.out.printf("%n%10d %10s %10d %10s %15s %15s %s ".formatted(i.getMemberId(),i.getName(),i.getAge(),i.getPlan(),i.getStartDate(),i.getEndDate(),i.getStatus()));
	}
	
	
	public void operationOnMember(Member editMember) {
		editMember.getPersonDetails();
		System.out.print("""
				
				
				1.Recharge
				2.Renew
				3.Change Name
				4.change Date of Birth
				5.Change Plan
				6.Change Start Date
				7.Change End Date
				8.Remove This Member
				9.Go To Trainer Menu
				Select a Method :
				""");
		int temp = Input.getNumberInRange(1, 8);
		if (temp ==1) {
			editMember.setEndDate(editMember.getEndDate().plusMonths(1));
		}else if(temp ==2) {
			editMember.setEndDate(editMember.getEndDate().plusMonths(1));
			editMember.setPlan(Input.getValidatedMembershipPlan());
		}else if (temp ==3) {
			editMember.setName(Input.getValidatedName());
		}else if (temp ==4) {
			editMember.setAge(Input.calculateAge(Input.getValidatedDOB()));
		}else if (temp ==5) {
			editMember.setPlan(Input.getValidatedMembershipPlan());
		}else if(temp ==6) {
			editMember.setStartDate(Input.getValidDateFromUser());
		}else if(temp ==7) {
			editMember.setEndDate(Input.getValidDateFromUser());
			
		}else if (temp ==8) {
			tempmembers.remove(editMember);
		}
	
		
	}
	
	

}
