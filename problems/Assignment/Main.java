package Assignment;

import java.util.*;

public class Main {
     public static void main(String [] args) {
    	 Scanner sc=new Scanner(System.in);
    	 boolean exit=false;
    	 
    	 System.out.println("welcome to Gym management System please enter your Gym name:");
    	 String Gymname=sc.nextLine();
    	 Gym gym=new Gym(Gymname);
    	
    	 while(true) {
    		 System.out.println("Pass values based on menu provided below:");
    		 System.out.println("enter 1 to add Member: \n enter 2 to create plan :\n enter 3 to assign plan to existing person: \n enter 4 to exit: \n enter q to remove memeber:"
    		 		);
    		 String input=sc.nextLine();
    		 switch(input) {
    		 case "1":{
    			 System.out.println("Enter name of the person:");
    			 String name=sc.nextLine();
    			 System.out.println("Enter age of the person:");
    			 int age=Integer.parseInt(sc.nextLine());
    			 System.out.println("Enter Memeber Ship Id of the person:");
    			 int memeberShipId=Integer.parseInt(sc.nextLine());
    			 gym.addMember(new Member(memeberShipId,age,name));
    			 break;
    		 }
    		 case "2":{
    			 System.out.println("Enter name of the Plan:");
    			 String name=sc.nextLine();
    			 System.out.println("Enter fee details of the Plan:");
    			 int fee=Integer.parseInt(sc.nextLine());
    			 System.out.println("Enter Duration details of the Plan in months:");
    			 int durationInMonths=Integer.parseInt(sc.nextLine());
    			 gym.addPlan(new MemberShipPlan(name,fee,durationInMonths));
    			 break;
    		 }
    		 case "3":{
    			 System.out.println("Eneter name of the person:");
    			 String name=sc.nextLine();
    			 System.out.println("Enter Memeber Ship Id of the person:");
    			 int memeberShipId=Integer.parseInt(sc.nextLine());
    			 try {
    				Member member= gym.findmemeber(name,memeberShipId);
    				if(member!=null) {
    					System.out.println(gym.getPlans());
    					 System.out.println("Enter 0 for Basic \n Enter 1 for Premium \n Enter 2 for Gold");
    				     String plan=sc.nextLine();
    				     switch(plan) {
    				     case "0":
    				    	 member.setPlan(gym.getPlansList().get(0));
    				    	 break;
    				     case "1":
    				    	 member.setPlan(gym.getPlansList().get(1));
    				    	 break;
    				     case "2":
    				    	 member.setPlan(gym.getPlansList().get(2));
    				    	 break;	
    				      default:
    				    	  System.out.println("Plan number should be within range of Options: ");
    				    	  break;
    				     }
    				     System.out.println("Operation completed");
    				     break;
    				}
    			 }catch(Exception e) {
    				 System.out.println("Eneter the valid memeber ship id");
    		          break;
    			 }
    		 }
    		 case "4":{
    			 System.out.println("Exiting the System");
    			 exit=true;
    			 break;
    		 }
    		 case "q":{
    			 System.out.println("Enter Memeber Ship Id of the person:");
    			 int memeberShipId=Integer.parseInt(sc.nextLine());
    			 System.out.println("Eneter name of the person:");
    			 String name=sc.nextLine();
    			 try {
    			 Member member= gym.findmemeber(name,memeberShipId);
    			 if(member!=null) {
					gym.removeMember(memeberShipId);
				}
    			 }catch(Exception e) {
    				 System.out.println("Enter the valid memeber ship id");
    				 break;
    			 }
    		 }
    		 }
    		 if(exit==true) {
        		 break;
        	 }
    		 
    	 }
    	
    	 
     }
}






