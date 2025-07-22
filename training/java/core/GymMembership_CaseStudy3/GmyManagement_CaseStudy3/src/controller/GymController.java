package controller;
import model.Member;
import model.MembershipPlan;
import servicesImplementation.GymService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import controllerInterface.GymControllerInterface;
import servicesInterface.GymServiceInterface;
public class GymController implements GymControllerInterface {
	
    GymServiceInterface service=new GymService();

    public void run() {
        Scanner s=new Scanner(System.in);
        int ch;

        while(true) {
            System.out.println("======================================================");
            System.out.println("\n====GYM MANAGEMENT SYSTEM====");
            System.out.println("1.Add New Member");
            System.out.println("2.Assign Plan");
            System.out.println("3.View All Members");
            System.out.println("4.Update Member Plan");
            System.out.println("5.Delete Members");
            System.out.println("6.Exit");
            System.out.print("Enter Your Choice: ");
            String input = s.nextLine();
            System.out.println("\n");

            try {
                ch=Integer.parseInt(input);
                

                if(ch==1) {
                    String id;
                    while(true) {
                        System.out.println("Enter Id Of The Member (Only Digits): ");
                        id=s.nextLine();
                        boolean isValidId=true;
                       for(int i=0;i<id.length();i++) {
                            if (!Character.isDigit(id.charAt(i))) {
                                isValidId=false;
                                break;
                            }
                        }
                        if (!isValidId) {
                            System.out.println("Id must contain digits only");
                            continue;
                        }
                        if(service.isMemberPresent(id))
                        {
                        	System.out.println("Member already exists");
                        }
                        else
                        	break;
                    }

                    String name;
                    while (true) {
                        System.out.println("Enter Name Of The Member: ");
                        name=s.nextLine();
                        boolean isValidName=true;
                       for(int i=0;i<name.length();i++)
                       {
                            char chname=name.charAt(i);
                            if (!Character.isLetter(chname) && chname!=' ') {
                                isValidName=false;
                                break;
                            }
                        }
                        if (!isValidName) {
                            System.out.println("Name must contain alphabets only.");
                            continue;
                        } else break;
                    }

                    String ageInput;
                    int age;
                    while (true) {
                        System.out.println("Enter Age Of The Member: ");
                        ageInput=s.nextLine();
                        boolean isNumeric=true;
                        for(int i=0;i<ageInput.length();i++)
                        {
                            if (!Character.isDigit(ageInput.charAt(i))) {
                                isNumeric=false;
                                break;
                            }
                        }
                        if (!isNumeric) {
                            System.out.println("Age must be a number.");
                            continue;
                        }
                        age=Integer.parseInt(ageInput);
                        if (age<=14 || age>100) {
                            System.out.println("Age must be between 14 and 100.");
                            continue;
                        } else break;
                    }

                    service.addMember(id,name,age);

                } 
                else if (ch == 2) {
                    String memberId;
                    while(true) {
                        System.out.println("Enter Member Id: ");
                        memberId=s.nextLine();
                        if (service.isMemberPresent(memberId)) {
                            break;
                        } else {
                            System.out.println("Member ID not found.");
                        }
                    }

                    List<MembershipPlan> plans = service.getAllPlans();
                    System.out.println("Available Plans:");
                    for (int i=0; i<plans.size();i++) {
                        MembershipPlan p=plans.get(i);
                        System.out.println(i+":"+p.getPlanName()+"-"+p.getDuration_in_months()+"Months -"+p.getFee()+" .Rs");
                    }

                    int planIndex;
                    while (true) {
                        System.out.println("Enter Plan Index: ");
                        String planIndexInput=s.nextLine();
                        boolean isValidPlanIndex=true;
                        for(int i=0;i<planIndexInput.length();i++)
                        {
                            if (!Character.isDigit(planIndexInput.charAt(i))) {
                                isValidPlanIndex=false;
                                break;
                            }
                        }
                        if (!isValidPlanIndex) {
                            System.out.println("Plan index must be a number.");
                            continue;
                        }

                        planIndex=Integer.parseInt(planIndexInput);
                        if (planIndex < 0 || planIndex >= plans.size()) {
                            System.out.println("Please enter a valid plan index.");
                            continue;
                            
                        } else 
                        	break;
                    }

                    service.assignPlan(memberId,planIndex);

                } else if(ch==3) {
                    List<Member> members=service.getAllMembers();
                    if (members.isEmpty()) {
                        System.out.println("No members found.");
                    } else {
                        for (Member m:members) {
                            service.showMemberDetails(m);
                        }
                    }

                } 
                else if(ch==4)
                {
                	String memberId;
                	while(true)
                	{
                		System.out.println("Enter Member ID");
                		memberId=s.nextLine();
                		if(service.isMemberPresent(memberId))
                		{
                			break;
                		}
                		else
                		{
                			System.out.println("memberId not found");
                		}
                		
                	}
                	    List<MembershipPlan> plans=service.getAllPlans();
                	    System.out.println("Available Plans:");
                	   for(int i=0;i<plans.size();i++)
                	   {
                	        MembershipPlan p = plans.get(i);
                	        System.out.println(i+":"+p.getPlanName()+"-"+p.getDuration_in_months()+"Months -"+p.getFee()+" .Rs");
                	    }

                	    int planIndex;
                	    while (true) {
                	        System.out.println("Enter New Plan Index: ");
                	        String planIndexInput=s.nextLine();
                	        boolean isValidPlanIndex=true;
                	        for(int i=0;i<planIndexInput.length();i++) {
                	            if (!Character.isDigit(planIndexInput.charAt(i))) {
                	                isValidPlanIndex=false;
                	                break;
                	            }
                	        }
                	        if (!isValidPlanIndex) {
                	            System.out.println("Plan index must be a number.");
                	            continue;
                	        }
                	        planIndex=Integer.parseInt(planIndexInput);
                	        if (planIndex<0 || planIndex>=plans.size()) {
                	            System.out.println("Please enter a valid plan index.");
                	            continue;
                	        } else 
                	        	break;
                	    }

                	    service.updateplan(memberId,planIndex);
                	
 
                }
                else if(ch==5)
                {
                	String id;
                	System.out.println("Enter member Id to delte");
                	id=s.nextLine();
                	service.deleteMember(id);
                
                }
                else if (ch == 6) {
                    System.out.println("Exiting...");
                    break;

                } else {
                    System.out.println("Invalid choice. Please try again");
                }

            } catch (SQLException e) {
            	e.printStackTrace();
                System.out.println("Database error: "+e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("Enter Valid Integer");
            }
        }

        s.close();
    }
}
