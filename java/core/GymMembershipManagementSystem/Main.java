package GymMembershipManagementSystem;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		Gym gym = new Gym();
        int choice;

        System.out.println("    Welcome to Gym Membership Management System App   ");
        do {
            System.out.println("1. Add New Member");
            System.out.println("2. Assign Membership Plan to Member");
            System.out.println("3. View All Members");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            try {
                choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1 -> {
                        Random r=new Random();
                        String id=Integer.toString(LocalDate.now().getYear());
                        int id1;
                        while(true) {
                        	id1=r.nextInt(1000);
                        	if(!Gym.members.containsKey(id+String.format("%03d", id1))) {
                        		break;
                        	}
                        };
                        id+=String.format("%03d", id1);
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        if(!Pattern.matches("^[A-Za-z ]+$", name)) {
                        	
                        	throw new InvalidDataException("Invalid Name.Please give me correct name");
                        }
                        System.out.print("Enter Member Gender (Male/Female/Others) : ");
                        String gender = sc.nextLine();
                        if(!gender.toLowerCase().equals("male")&&!gender.toLowerCase().equals("female")&&!gender.toLowerCase().equals("others")) {
                        	throw new InvalidDataException("Invalid Gender");
                        }
                        else {
                        	gender=gender.substring(0, 1).toUpperCase()+gender.substring(1).toLowerCase();
                        }
                        System.out.print("Enter Member Date of Birth (DD-MM-YYYY) : ");
                        String date=sc.nextLine();
                        LocalDate dob;
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                        try {
                        	dob=LocalDate.parse(date, format);
                        	if(dob.isAfter(LocalDate.now())) {
                        		throw new InvalidDataException("Given date "+date+" is not valid");
                        	}
                        	int age=Period.between(dob, LocalDate.now()).getYears();
                        	if(age>65||age<10) {
                        		throw new InvalidDataException("Sorry to say this but your age is not preferrable for gym");
                        	}
                        }
                        catch(Exception e) {
                        	throw new InvalidDataException("Given date "+date+" is not in correct format");
                        }
                        System.out.print("Enter Member Weight: ");
                        double weight = sc.nextDouble();
                        System.out.print("Enter Member Date of Joining (DD-MM-YYYY) : ");
                        sc.nextLine();
                        String doj=sc.nextLine();
                        LocalDate dateOfJoining;
                        try {
                        	dateOfJoining=LocalDate.parse(doj,format);
                        	if(dateOfJoining.isAfter(LocalDate.now())||dateOfJoining.isBefore(dob)) {
                        		throw new InvalidDataException("Given date "+date+" is not valid");
                        	}
                        }
                        catch(Exception e) {
                        	throw new InvalidDataException("Given date "+date+" is not in correct format");
                        }
                        gym.addMember(id,name,gender,dob,weight,dateOfJoining);
                    }

                    case 2 -> {
                        System.out.print("Enter Member ID: ");
                        String memberId = sc.nextLine();
                        gym.showAllPlans();
                        System.out.print("Choose plan index: ");
                        int planIndex = sc.nextInt();
                        sc.nextLine();
                        gym.assignPlanToMember(memberId, planIndex);
                        }

                    case 3 -> {
                    	System.out.println("     Registered Gym Mermbers List    ");
                        gym.viewAllMembers();
                       	}

                    case 4 -> {
                        System.out.println("	Exiting system....");
                    }

                    default -> {
                        System.out.println("	Invalid choice input. Please enter numbers only in the range of (1-4).");
                    }
                }

            } catch (InputMismatchException exception) {
                System.out.println("	Invalid input (InputMisMatch). Please enter correct input  for the smooth preocess...");
                sc.nextLine(); 
                choice = 0;
            }
        } while (choice != 4);

        sc.close();
	}

}