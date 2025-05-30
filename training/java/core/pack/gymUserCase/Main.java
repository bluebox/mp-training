package gymUserCase;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
     while(true) {
    	 System.out.println("Enter the number");
    	 System.out.println("-1 to quit");
    	 System.out.println("0 to Add The gym member");
    	 System.out.println("1 to remove the gym member");
    	 System.out.println("2 to update gym member");
    	 System.out.println("3 to assign membership(assign plan)");
    	 System.out.println("4 to display the gym members along with membership details");
    	 int input=sc.nextInt();
    	 
    	 switch(input) {
    	 case -1:
    		 System.out.println("Good bye Thank you visit again");
    		 return ;
    	 
    	 case 0:
    		 System.out.println("enter the following details to add:\n name \n age ");
    		 
    		 System.out.println("User added Succesfully");
    		 break;
    		 
    	 case 1:
    		 System.out.println("enter the memberId to remove the member from the gym");
    		 
    		 System.out.println("User removed succesfully");
    		 break;
    		 
    	 case 2: 
    		 System.out.println("enter the memberId and fields need to be updated ");
    		 
    		 System.out.println("User details updated Sucessfully");
    		 break;
    		 
    		 
    	 case 3:
    		 System.out.println("enter the memberId and plan details to provide membership");
    		 
    		 System.out.println("Membership assigned succesfully");
    		 break; 
    		 
    	 case 4:
    		 System.out.println("Here is the list of all gym members");
    		 
    		 break;
    		 
    	default:
    		System.out.println("Please enter the valid input(-1/0/1/2/3/4_)");
    	 } 
     }
	}

}
