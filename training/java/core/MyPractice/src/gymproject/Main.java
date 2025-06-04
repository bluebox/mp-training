package gymproject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws InvalidPlanException{
        Scanner sc = new Scanner(System.in);
        GymService gymService = new GymService();
        while (true) {
            System.out.println("\n===== Gym Membership System =====");
            System.out.println("1. Add New Member using Scanner Class");
            System.out.println("2. Add New Member using files");
            System.out.println("3. Assign Membership Plan");
            System.out.println("4. View All Members");
            System.out.println("5. View All Plans");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;
            try {
                choice = Integer.parseInt(sc.nextLine()); 
            } catch (Exception e) {
                System.out.println("Check the input format again! Please enter only numeric data.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println("Enter ID: ");
                    String id = sc.nextLine();
                    if(gymService.existsById(id)){
                        System.out.println("Member already exists");
                        break;
                    }

                    System.out.println("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = Integer.parseInt(sc.nextLine());

                    gymService.addMember(id, name, age);
                    break;
                case 3:
                    System.out.print("Enter ID: ");
                    id = sc.nextLine();
                    if(!gymService.existsById(id)){
                        System.out.println("User Doesn't exists");
                        break;
                    }
                    System.out.print("Enter Plan Name (Basic/Premium/Gold): ");
                    String plan = sc.nextLine();
                    String lowerPlan=plan.toLowerCase();
                    if(lowerPlan.equals("basic") || (lowerPlan.equals("premium")) || (lowerPlan.equals("gold"))){
                        gymService.assignPlan(id, lowerPlan);
                    }
                    else{
                        System.out.println("Please enter valid plan : (Gold/Premium/Basic)");
                    }
                    break;

                case 4:
                	System.out.println();
                    gymService.viewAllMembers();
                    break;

                case 5:
                    gymService.viewAllPlans();
                    break;
                case 6:
                    System.out.println("\nExiting the system. Goodbye!");
                    return;
                case 2:
                	System.out.println("\nEnter file name:");
                	String line;
//            		String filePath="samplecodes/data.csv";
                	String filePath=sc.nextLine();
            		try {
            			BufferedReader br=new BufferedReader(new FileReader(filePath));
            			int i=0;
            			while((line=br.readLine())!=null) {
            				String[] text=line.split(",");
            				if(i!=0) {
            					id=text[0];
            					name=text[1];
            					age=Integer.parseInt(text[2]);
            					gymService.addMember(id, name, age);
            				}
            				i++;
            			}
            			
            		}
            		catch(Exception e) {
            			System.out.println(e.getMessage());
            			System.out.println("Please check the file ");
            		}
            		break;
                	
                default:
                    System.out.println("\nInvalid choice. Please select a valid option.");
            }
            System.out.println();       
        }
    }
}