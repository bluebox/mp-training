 import java.util.*;
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gym g=new Gym();
		Scanner s=new Scanner(System.in);
		int ch;
		while(true)
		{
			System.out.println("======================================================");
			System.out.println("\n====GYM MANAGEMENT SYSTEM====");
			System.out.println("1.Add New Member");
			System.out.println("2.Assign Plan");
			System.out.println("3.View All Members");
			System.out.println("4.Exit");	
			System.out.println("Enter Your Choice:");
			String input=s.nextLine();
			try
			{
				
				ch =Integer.parseInt(input);
				
				
				if(ch==1)
				{
					
					
					String id;
					while(true)
					{
					System.out.println("Enter Id Of The Member(Only Digits): ");
					id=s.nextLine();
					boolean isValidId=true;
					for(int i=0;i<id.length();i++)
					{
						if(Character.isDigit(id.charAt(i))==false)
						{
							isValidId=false;
							break;
						}
					}
					if(isValidId==false)
					{
						System.out.println("Id must be contains Digits only");
						continue;
					}
					else
						break;
					}
						
					
					String name;
					while(true)
					{
					System.out.println("Enter Name Of The Member: ");
					name=s.nextLine();
					
					boolean isValidName=true;
					for(int i=0;i<name.length();i++)
					{
						char chname=name.charAt(i);
						if(Character.isLetter(chname)==false && chname!=' ')
						{
							isValidName=false;
							break;
						}
					}
					if(isValidName==false)
					{
						System.out.println("Name Must be in Alphabets");
						continue;
					}
					else
						break;
					}
					
					String ageInput;
					while(true)
					{
					System.out.println("Enter Age Of The Memebr: ");
					ageInput=s.nextLine();
					boolean isNumeric=true;
					for(int i=0;i<ageInput.length();i++)
					{
						if(Character.isDigit(ageInput.charAt(i))==false)
						{
							isNumeric=false;
							break;
						}
					}
					if(isNumeric==false)
					{
						System.out.println("Age must be number");
						continue;
					}
					

					
					int age=Integer.parseInt(ageInput);
					if(age<=0|| age>100)
					{
					System.out.println("Age must be between 1 and 100");
					continue;
					}
					else
					{
						g.addMember(id,name,age);
						break;
					}
						
					
				
					
					
					
				}
				}
				else if(ch==2)
				{
					
					String memberId;
					while(true)
					{
					System.out.println("Enter Member Id: ");
					 memberId=s.nextLine();
					
					if(g.isMemberPresent(memberId)==false)
					{
						System.out.println("Member id is not found");
						continue;
					}
					else
						break;
					}
					
					System.out.println("Available Plans: ");
					g.showPlans();
					
					int plan;
					while(true)
					{
						System.out.println("Enter Plan Index: ");
						String planIndex=s.nextLine();
						 boolean isValidPlanIndex = true;
					        for(int i=0;i<planIndex.length();i++)
					        {
					            if(Character.isDigit(planIndex.charAt(i))==false)
					            {
					                isValidPlanIndex = false;
					                break;
					            }
					        }

					        if(isValidPlanIndex==false)
					        {
					            System.out.println("Plan index must be a number");
					            continue;
					        }
					         plan=Integer.parseInt(planIndex);

						if(plan<0 || plan>=2)
						{
							System.out.println("Please enter valid plan");
							continue;
							
						}
						else
							break;
						
							
					}
					g.assignPlan(memberId, plan);
					
				}
				else if(ch==3)
				{
					g.showAllMembers();
				}
				else if(ch==4)
				{
					System.out.println("Exiting...");
					break;
				}
				
			}
			catch(Exception e)
			{
				System.out.println("Enter Valid Integer");
			     continue;
			}
		}

	}

}
