import java.util.*;

public class Gym {
     private static int cnt=0;
     private List<Member>members;
     public Gym() {
    	 members=new ArrayList<>();
     }
     public int getSize() {
    	 return cnt;
     }
     public void addMember(Member newNumber) {
    	  if(members.add(newNumber)) {
    		  System.out.println("added successfully");
    		  cnt++;
    		  return ;
    	  }
    	  System.out.println("error occured");
     }
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
     public void getAllMember(){
    	 System.out.println("sorted by name");
    	 List<Member> member=new ArrayList<>();
    	 for(Member ele:members)member.add(ele);
    	 member.sort((a,b)->a.getName().compareTo(b.getName()));
    	 for(Member ele:member) {
    		 ele.getDetails();
    	 }
     }
     public void getAllMemberByJoiningDate(){
    	 System.out.println("orderedy by Date");
    	 List<Member> member=new ArrayList<>();
    	 for(Member ele:members)member.add(ele);
    	 member.sort((a,b)->a.getJoiningDate().compareTo(b.getJoiningDate()));
    	 for(Member ele:member) {
    		 ele.getDetails();
    	 }
     }
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
    	 System.out.println("User not found");
     }
     public void getByAge(int age) {
    	 System.out.println("Members with age "+age);
    	 for(Member ele:this.members) {
    		  if(ele.getAge()==age)ele.getDetails();
    	 }
     }
}
