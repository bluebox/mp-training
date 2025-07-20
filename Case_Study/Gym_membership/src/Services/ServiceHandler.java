
package Services;

import DAO.Databasemanager;

public class ServiceHandler {
	Databasemanager dbmanager=new Databasemanager();
	Gym gym=new Gym();
	public void addmember(String name,int age,int memberid) {
		Member member=new Member(name, age, memberid);
		boolean memberexist=dbmanager.addmemberstodatabase(member);
		if(!memberexist)
        gym.Addmember(member);
        //member.show_details();
		
	}
	public void addmembership(int memberid,String planname,int planduration,int fee) {
		 MembershipPlan membershipplan=new MembershipPlan(planname, planduration, fee);
         dbmanager.addmembership(memberid, membershipplan);
		 gym.assignPlan(memberid, membershipplan);
	}
	public void showallmembers() {
		dbmanager.showmembersdb();
		//gym.show_All_Members();
	}
	public void updatemembership(int memberid,String planname,int duration,int fee) {
		dbmanager.updatemembershipplan(memberid,planname,duration,fee);
	}
	public void addplans(String planname,int duration,int fee) {
		dbmanager.addmemberplans(new MembershipPlan(planname,duration,fee));
		
	}
	public void deletemembership(int memberid) {
		dbmanager.deletemembershipidfromdatabase(memberid);
	}
	
	
	
} 
