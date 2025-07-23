package Controller;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import Services.Member;
import Services.MembershipPlan;
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
               
                try {
					servicehandler.addmember(name, age, memberid);
				} catch (SQLIntegrityConstraintViolationException e) {
					// TODO Auto-generated catch block
					System.out.println("memberid already exists");
				}
                
                
            }
            else if(choice==2){
            		servicehandler.showplans();
            		List<MembershipPlan> list=servicehandler.membershipplans;
            		System.out.println(list);
            		if(list.size()>0)
            		for(MembershipPlan m:list) {
            			m.planDetails();
            		}
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
                

               
                System.out.println("Enter the membershipplan id");
                int membershipplanid=0;
             	try{
             		  membershipplanid=sc.nextInt();
             		 sc.nextLine();
              }
              catch(InputMismatchException i){
                  
                  System.out.println("invalid plan id enter correct plan id ");
                  sc.nextLine();
                  continue;
              }
               
                
                
                
                boolean res=servicehandler.addmembership(memberid, membershipplanid);
                if(!res) {
                	System.out.println(" memberid does not exist or membershipplan already exists");
                }
               

            }
            else if(choice==3){
            	
            List<Member> memberlist;
			try {
				memberlist = servicehandler.showallmembers();
				 for(Member member:memberlist) {
		            	member.show_details();
		            	if(member.getMembershipPlan()!=null)
		            	{
		            		member.getMembershipPlan().planDetails();
		            	}
		            	else {
		            		System.out.println("no plans assigned yet");
		            }
		            }
			} catch (SQLIntegrityConstraintViolationException e) {
				// TODO Auto-generated catch block
				System.out.println("membershipplan id is wrong ");
			}
           
            	
                
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
            	 while(true) {
	             	try{
	             		membershipplanfee=sc.nextInt();
	              }
	              catch(InputMismatchException i){
	                  
	                  System.out.println("invalid plan fee enter correct plan fee in ");
	                  sc.nextLine();
	                  continue;
	              }
	             	if(membershipplanfee>=0) {
	             		break;
	             	}
	             	System.out.println("enter valid fee");
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
            	try {
					servicehandler.addplans(membershipplanname, membershipplanduration, membershipplanfee);
				} catch (SQLIntegrityConstraintViolationException e) {
					// TODO Auto-generated catch block
					System.out.println("plan name already exists");
				}
            	
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
            	boolean res=servicehandler.deletemembership(memberid);
            	
            	if(res)
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
