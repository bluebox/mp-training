
package Services;


import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import DAO.Databasemanager;

public class ServiceHandler {
	Databasemanager dbmanager=new Databasemanager();
	public List<MembershipPlan> membershipplans=new ArrayList<>();
	
	Gym gym=new Gym();
	public void addmember(String name,int age,int memberid) throws SQLIntegrityConstraintViolationException {
		Member member=new Member(name, age, memberid);
		boolean memberexist=dbmanager.addmemberstodatabase(member);
		if(!memberexist)
        gym.Addmember(member);
        //member.show_details();
		
	}
	public boolean addmembership(int memberid,int membershipplanid) {
		
         return dbmanager.addmembership(memberid, membershipplanid);
         
        
		 //gym.assignPlan(memberid, membershipplanid);
	}
	public List<Member> showallmembers() throws SQLIntegrityConstraintViolationException {
		return dbmanager.showmembersdb();
		//gym.show_All_Members();
	}
	public void updatemembership(int memberid,String planname,int duration,int fee) {
		dbmanager.updatemembershipplan(memberid,planname,duration,fee);
	}
	public void addplans(String planname,int duration,int fee) throws SQLIntegrityConstraintViolationException {
		dbmanager.addmemberplans(new MembershipPlan(planname,duration,fee,0));
		
	}
	public boolean  deletemembership(int memberid) {
		 return dbmanager.deletemembershipidfromdatabase(memberid);
	}
	public void showplans() {
		 
		 
		membershipplans=dbmanager.showplans();
		 System.out.println(membershipplans);
		 for(MembershipPlan m:membershipplans) {
			 System.out.println("name"+m.getPlanName());
		 }
	}
	
	
	
} 
