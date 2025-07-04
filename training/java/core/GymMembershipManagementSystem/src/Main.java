import java.util.Scanner;

public class Main {
	
	static Scanner sc = new Scanner(System.in);
	static Gym gym = new Gym();
	
	public static void main(String[] args) {
		
		boolean exit = false;
		
		while(!exit) {
			printOptions();
		
		try {
			int choice = Integer.parseInt(sc.nextLine());
			
			switch (choice) {
            case 1:
            	addMember();
            	break;
            	
            case 2:
            	String idForPlan = isValidId();
                if(idForPlan == "1") {
                	break;
                }
                System.out.println("Available Plans:");
                gym.showAvailablePlans();
                System.out.print("Select plan number: ");
                try {
	                int planChoice = Integer.parseInt(sc.nextLine());
	                gym.assignPlanToMember(idForPlan, planChoice);
	                break;
                }catch(NumberFormatException e){
                	break;
                }
            case 3:
                gym.viewAllMembers();
                break;

            case 4:
                exit = true;
                System.out.println("Exiting system. Goodbye!");
                break;

            default:
                System.out.println("Invalid choice. Try again.");
        }
		}catch (NumberFormatException e) {
			System.out.println("Please enter numbers Choice.");
        }catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
		}
	}
	
	public static boolean check(String option) {
		if(option.toLowerCase().equals("menu")) {
			return true;
		}
		return false;
		
	}
	private static void addMember() {
		memberIdCheck();
	}
	
	private static void memberIdCheck() {
		
		System.out.print("Enter Member ID: ");
		String memberId = sc.nextLine();
		
		if(Main.check(memberId)) {
			return;
		}
		
		if(!memberId.matches("^[0-9]+$"))
		{
			System.out.println("InValid ID Number!");
			memberIdCheck();
		}else if(gym.findMemberById(memberId) != null) {
			System.out.println("Id already Exists! Please Chose another Id!");
			memberIdCheck();
		}
		else {
			memberNameCheck(memberId);
		}
	    			  
	}
	
	private static void memberNameCheck(String memberId) {
		
		System.out.println("Enter Name: ");
        String name = sc.nextLine();
        
        if(Main.check(name)) {
        	return;
        }
        
        if(!name.matches("^[A-za-z]{2,30}$")) {
        	System.out.println("InValid Name!");
        	memberNameCheck(memberId);
        }
        else {
        	memberAgeCheck(memberId,name);
        }
        
	}
	
	private static void memberAgeCheck(String memberId,String name) {
		
		System.out.print("Enter Age: ");
    	String age = sc.nextLine();
    	
    	if(Main.check(age)) {
    		return;
    	}
    	
    	if( (!age.matches("^[0-9]+$")) || (Integer.parseInt(age) < 0 || Integer.parseInt(age) > 100) ) {
    		System.out.println("Age must be in Range 1-99");
    		memberAgeCheck(memberId,name);
    	}else {
 
	    	Member newMember = new Member(memberId, name,Integer.parseInt(age));
			gym.addMember(newMember);
    	}
	}
	
	private static String isValidId() {
		System.out.print("Enter Member ID to assign plan: ");
        String idForPlan = sc.nextLine();
        
        if(check(idForPlan)) {
        	return "1";
        }
        
        if(gym.findMemberById(idForPlan) != null)
        {
        	return idForPlan;
        }
        else {
        	System.out.println("Member Id not Found!");
        	return isValidId();
        }
        
	}
	
	private static void printOptions() {
		System.out.println("\n--- Gym Membership Management System ---");
        System.out.println("1. Add New Member");
        System.out.println("2. Assign Membership Plan to Member");
        System.out.println("3. View All Members and Details");
        System.out.println("4. Exit");
        System.out.println("menu for geting Options");
        System.out.print("Enter your choice: ");
	}
	
}
