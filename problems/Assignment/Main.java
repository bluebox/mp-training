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


class Gym{
	private  ArrayList<Member> members=new ArrayList<>();
	private  ArrayList<MemberShipPlan> plans=new ArrayList<>();
	private String name;
	
	public Gym(String name) {
		this.name=name;
	}
	
	public String getName() {
		return name;
	}
	
	public Member findmemeber(String name,int Id) {
		Member required=null;	
		for(int i=0;i<members.size();i++) {
			if(members.get(i).getMemeberShipId()==Id) {
				required= members.get(i);
			}
		}
		if(required==null)throw new IndexOutOfBoundsException();
				
		return required;	
	}
	
	public String getMemebers(){
		StringBuilder response=new StringBuilder();
		for(int i=0;i<members.size();i++) {
			if(members.get(i).getPlan() != null) {
			response.append("The MemebershipID : "+members.get(i).getMemeberShipId()+"the plan is "+members.get(i).getPlan().getName()+"\n");
		}else{
			response.append("The MemebershipID : "+members.get(i).getMemeberShipId()+"the plan is not yet assigned\n");
		}
		}
		return response.toString();
	}
	
	public String getPlans(){
		StringBuilder response=new StringBuilder();
		for(int i=0;i<plans.size();i++) {
			response.append("The plan is "+plans.get(i).getName()+"its duration is "+plans.get(i).getDuration()+"its fee per year is "+plans.get(i).getFee()+"\n");
		};
		return response.toString();
	}
	
	public ArrayList<MemberShipPlan>  getPlansList(){
	return plans;
	}
	public void addMember(Member person ) {
		System.out.println("The new member is added");
		members.add(person);
	}
	
	public void addPlan(MemberShipPlan plan) {
		System.out.println("The new plan is added");
		plans.add(plan);
	}
	
	public void removeMember(int memeberShipID) {
		System.out.println("The member is being removed");
		for(Member member:members) {
			if(member.getMemeberShipId()==memeberShipID) {
				members.remove(member);
			}
		}
		System.out.println("The member is successfully removed");
	}
	
	
}

class MemberShipPlan{

	private String name;
	private int fee;
	private int duration;
	
	public MemberShipPlan() {
		
	}
	
	public MemberShipPlan(String name, int fee, int duration) {
		this.name = name;
		this.fee = fee;
		this.duration = duration;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getFee() {
		return fee;
	}
	public void setFee(int fee) {
		this.fee = fee;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	
	
}

class Member extends Person{
	private  int memeberShipId;
	private  MemberShipPlan plan;
	  
	  public Member(int age,String name) {
		  super(age,name);
	  }
	  
	  public Member(int memeber,int age,String name) {
		  this(memeber,null,age,name);
	  }
	  
	  public Member(int memeberShipId,MemberShipPlan plan,int age,String name) {
		  super(age,name);
		  this.memeberShipId=memeberShipId;
		  this.plan=plan;
	  }

	public int getMemeberShipId() {
		return memeberShipId;
	}

	public void setMemeberShipId(int memeberShipId) {
		this.memeberShipId = memeberShipId;
	}

	public MemberShipPlan getPlan() {
		return plan;
	}

	public void setPlan(MemberShipPlan plan) {
		this.plan = plan;
	}
	  
	  
	  
}

abstract class Person{
	private int age;
	private String name;
	
	public Person() {
		
	}
	
	public Person(int age,String name) {
		this.age=age;
		this.name=name;
	}
	
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
