import java.util.*;
interface GymInterface{
	void addMember(Member newMember);
	void getByPlan(String plan);
	void getAllMember();
	void getAllMemberByJoiningDate();
	void deleteById(int id);
}
public class Gym implements GymInterface{
     private int cnt=0;
     private List<Member>members;
     public Gym() {
    	 members=new ArrayList<>();
     }
     public int getId() {
    	 return cnt;
     }
     @Override
     public void addMember(Member newNumber) {
    	  if(members.add(newNumber)) {
    		  System.out.println("added successfully");
    		  cnt++;
    		  return ;
    	  }
    	  System.out.println("error occured");
     }
     @Override
     public void getByPlan(String plan){
    	 System.out.println("members with "+plan+" plan");
    	 List<Member> val=new ArrayList<>();
    	 for(Member ele:members) {
    		 if(ele.getMemberShip().getPlan()==plan) {
    			 val.add(ele);
    		 }
    	 }
    	 for(Member ele:val) {
    		 ele.getDetails();
    	 }
     }
     @Override
     public void getAllMember(){
    	 System.out.println("sorted by name");
    	 List<Member> member=new ArrayList<>();
    	 for(Member ele:members)member.add(ele);
    	 member.sort((a,b)->a.getName().compareTo(b.getName()));
    	 for(Member ele:member) {
    		 ele.getDetails();
    	 }
     }
     @Override
     public void getAllMemberByJoiningDate(){
    	 System.out.println("orderedy by Date");
    	 List<Member> member=new ArrayList<>();
    	 for(Member ele:members)member.add(ele);
    	 member.sort((a,b)->a.getJoiningDate().compareTo(b.getJoiningDate()));
    	 for(Member ele:member) {
    		 ele.getDetails();
    	 }
     }
     @Override
     public void deleteById(int id) {
    	 Member val=null;
    	 for(Member ele:this.members) {
    		 if(ele.getId()==id) {
    			 val=ele;
    			 break;
    		 }
    	 }
    	 if(val!=null) {
    		 members.remove(val);
    		 System.out.println("user deleted successfully of id "+id);
    		 return ;
    	 }
    	 System.out.println("User not found to delete");
     }
     public void getByAge(int age) {
    	 System.out.println("Members with age "+age);
    	 for(Member ele:this.members) {
    		  if(ele.getAge()==age)ele.getDetails();
    	 }
     }
}
