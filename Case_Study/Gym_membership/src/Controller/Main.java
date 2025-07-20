package Controller;

import java.util.InputMismatchException;
import java.util.Scanner;

import Services.ServiceHandler;
public class Main {	
    public static void main(String[] args) {
       Scanner sc=new Scanner(System.in);
        
        ServiceHandler servicehandler=new ServiceHandler();
        while(true){
             System.out.println("enter the number \n (1)  add Members \n (2) assign membership plan to a member \n (3) view all Registered members \n (4) update membership plan  \n (5) add plans  \n (6) Remove membership \n (7) enter any character to exit\n");
            int choice;
            try{
                choice=sc.nextInt();
                sc.nextLine();
            }
            catch(InputMismatchException i){
                sc.nextLine();
                System.out.println("invalid choice");
                break;
            }
            if(choice==1){
                
                System.out.println("Enter the details of member");
                System.out.println("Enter name of member");
                String name="";
                try {
                	name=sc.nextLine();
                }
                catch(InputMismatchException i){
                    
                    System.out.println("invalid age enter correct age");
                    sc.nextLine();
                    continue;
                }
                System.out.println("Enter the age");
                int age=0;
                while(true) {
                	try{
                		age=sc.nextInt();
                		sc.nextLine();
                    }
                    catch(InputMismatchException i){
                        
                        System.out.println("invalid age enter correct age");
                        sc.nextLine();
                        continue;
                    }
                
                	if(age>0) {
                		break;
                	}
                	System.out.println("Enter correct age");
                }
            
                System.out.println("Enter the memberid");
                int memberid=0;
            	try{
            		  memberid=sc.nextInt();
            		  sc.nextLine();
                }
                catch(InputMismatchException i){
                    
                    System.out.println("invalid memberid enter correct memberid");
                    sc.nextLine();
                    continue;
                }
               
                servicehandler.addmember(name, age, memberid);
                
                
            }
            else if(choice==2){
                System.out.println("Enter the Memberid");
                int memberid=0;
            	try{
            		  memberid=sc.nextInt();
            		  sc.nextLine();
                }
                catch(InputMismatchException i){
                    
                    System.out.println("invalid memberid enter correct memberid");
                    sc.nextLine();
                    continue;
                }
                
                System.out.println("Enter the planname");
                String planname="";
            	try{
            		  planname=sc.next();
              }
              catch(InputMismatchException i){
                  
                  System.out.println("invalid planname enter correct planname");
                  sc.nextLine();
                  continue;
              }
               
                System.out.println("Enter the plan duration in months");
                int planDuration=0;
             	try{
             		  planDuration=sc.nextInt();
             		 sc.nextLine();
              }
              catch(InputMismatchException i){
                  
                  System.out.println("invalid plan duration enter correct plan duration in months");
                  sc.nextLine();
                  continue;
              }
               
                System.out.println("Enter the fee");
	                int fee=0;
	             	try{
	             		 fee=sc.nextInt();
	             		sc.nextLine();
	              }
	              catch(InputMismatchException i){
	                  
	                  System.out.println("invalid plan fee enter correct plan fee in ");
	                  sc.nextLine();
	                  continue;
	              }
                
                servicehandler.addmembership(memberid, planname, planDuration, fee);
                
               

            }
            else if(choice==3){
            	servicehandler.showallmembers();
                
            }
            else if(choice==4) {
            	System.out.println("Enter the memberid");
            	 int memberid=0;
             	try{
             		  memberid=sc.nextInt();
             		 sc.nextLine();
                 }
                 catch(InputMismatchException i){
                     
                     System.out.println("invalid memberid enter correct memberid");
                     sc.nextLine();
                     continue;
                 }
            	System.out.println("Enter the membershipplan name");
            	String membershipplanname="";
            	try{
            		membershipplanname=sc.next();
               }
               catch(InputMismatchException i){
                   
                   System.out.println("invalid membershipplanname enter correct membershipplanname");
                   sc.nextLine();
                   continue;
               }
            	
            
            	System.out.println("Enter the membershipplan duration");
            	 int membershipplanduration=0;
              	try{
              		membershipplanduration=sc.nextInt();
              		sc.nextLine();
               }
               catch(InputMismatchException i){
                   
                   System.out.println("invalid planduration  enter correct planduration  in ");
                   sc.nextLine();
                   continue;
               }
            	
            	System.out.println("Enter the membershipid fee");
            	int membershipplanfee=0;
            	 
	             	try{
	             		membershipplanfee=sc.nextInt();
	              }
	              catch(InputMismatchException i){
	                  
	                  System.out.println("invalid plan fee enter correct plan fee in ");
	                  sc.nextLine();
	                  continue;
	              }
            	servicehandler.updatemembership(memberid,membershipplanname , membershipplanduration, membershipplanfee);
            	
            }
            else if(choice==5) {
            	System.out.println("Enter the membershipplan name");
            	String membershipplanname="";
            	try{
            		membershipplanname=sc.next();
               }
               catch(InputMismatchException i){
                   
                   System.out.println("invalid membershipplanname enter correct membershipplanname");
                   sc.nextLine();
                   continue;
               }
            	System.out.println("Enter the membershipplan duration");
            	int membershipplanduration=0;
              	try{
              		membershipplanduration=sc.nextInt();
              		sc.nextLine();
               }
               catch(InputMismatchException i){
                   
                   System.out.println("invalid planduration  enter correct planduration  in ");
                   sc.nextLine();
                   continue;
               }
            	System.out.println("Enter the membershipid fee");
            	int membershipplanfee=0;
           	 
             	try{
             		membershipplanfee=sc.nextInt();
             		sc.nextLine();
              }
              catch(InputMismatchException i){
                  
                  System.out.println("invalid plan fee enter correct plan fee in ");
                  sc.nextLine();
                  continue;
              }
            	servicehandler.addplans(membershipplanname, membershipplanduration, membershipplanfee);
            	
            }
            else if(choice==6) {
            	System.out.println("Enter the memberid to remove membershipplan");
            	 int memberid=0;
              	try{
              		  memberid=sc.nextInt();
              		 sc.nextLine();
                  }
                  catch(InputMismatchException i){
                      
                      System.out.println("invalid memberid enter correct memberid");
                      sc.nextLine();
                      continue;
                  }
            	servicehandler.deletemembership(memberid);
            	System.out.println("successfully removed");
            }
            else if(choice==7){
            		
                break;
            }
            else{
                System.out.println("invalid number");
                continue;
            }
        }
        sc.close();
    
}
}
