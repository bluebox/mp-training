package DAO;

import Services.Member;
import Services.MembershipPlan;

import java.sql.*;
public class Databasemanager {
	private final String url="jdbc:mysql://127.0.0.1:3306/gym";
	private final String username="root";
	private final String password="Santhosh@123";	
	public Connection connectionestablish() {
		Connection c=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		try {
			c = DriverManager.getConnection(url, username, password);
			System.out.println("connection established with db");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
		

	}
	public void addmemberplans(MembershipPlan membership) {
		
		Connection c=connectionestablish();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int membershipid=getmembershipid(membership.getPlanName(),membership.getDurationMonths(),membership.getFee(),c);
		if(membershipid!=-1) {
			System.out.println("plan already exists");
			try {
				c.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
		}
		String query="insert into gym.membershipplan(membershipplanname,membershipplanduration,membershipplanfee) values(?,?,?)";
		try {
			PreparedStatement preparedstatement=c.prepareStatement(query);
		
			preparedstatement.setString(1,membership.getPlanName());
			preparedstatement.setInt(2,membership.getDurationMonths());
			preparedstatement.setInt(3,membership.getFee());
			preparedstatement.execute();
			c.commit();
			c.close();
			
		}
		catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	public int getmembershipid(String planname,int duration,int fee,Connection c) {
		
		int id=-1;
		String querytofindmembershipid="select * from gym.membershipplan where membershipplanname=? and membershipplanduration=? and membershipplanfee=?";
		try {
			PreparedStatement preparestatementforid=c.prepareStatement(querytofindmembershipid);
			preparestatementforid.setString(1,planname );
			preparestatementforid.setInt(2, duration);
			preparestatementforid.setInt(3, fee);
			ResultSet res=preparestatementforid.executeQuery(); 
			if(res.next()) {
				id=res.getInt("membershipplanid");
			}
			

		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return id;
	}
	public boolean checkmemberid(int memberid,Connection c) {
		
		
		String querytofindmembershipid="select * from gym.members where memberid=?";
		try {
			PreparedStatement preparestatementforid=c.prepareStatement(querytofindmembershipid);
			preparestatementforid.setInt(1,memberid);
			
			ResultSet res=preparestatementforid.executeQuery(); 
			if(res.next()) {
				return true;
			}

		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return false;
	}
	public void addmembership(int memberid,MembershipPlan membership)  {
		Connection c=connectionestablish();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean ifmemberexistsornot=checkmemberid(memberid,c);
		if(!ifmemberexistsornot) {
			System.out.println("memberid not available");
			try {
				c.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		String querytofindmembershipid="select * from gym.members where memberid=?";
		
		String querytoshowplans="select * from gym.membershipplan";
		String query="update gym.members  SET membershipplanid=? where memberid=?";
		int membershipid=getmembershipid(membership.getPlanName(),membership.getDurationMonths(),membership.getFee(),c);
		try {
			
			Statement statement=c.createStatement();
			PreparedStatement  preparestatement=c.prepareStatement(query);
			PreparedStatement  preparestatementtofindmembershipid=c.prepareStatement(querytofindmembershipid);
			preparestatementtofindmembershipid.setInt(1, memberid);
			ResultSet resformembershipid=preparestatementtofindmembershipid.executeQuery();
			resformembershipid.next();
			Integer membershipplanid = (Integer) resformembershipid.getObject("membershipplanid");
			if(membershipplanid!=null) {
				c.rollback();
				System.out.println("membershipplan already exists \n if you want to change please select (4) option to update membershipplan");
				
			}
			if(membershipid==-1) {
				c.rollback();
				System.out.println("no membershipplan with given details,please enter the correct details available plans are");
				ResultSet result=statement.executeQuery(querytoshowplans);
				while(result.next()) {
					System.out.print("plan name "+result.getString("membershipplanname"));
					System.out.print(" plan duration "+result.getInt("membershipplanduration"));
					System.out.println(" plan fee "+result.getInt("membershipplanfee"));
					
				}
				return;
				
			}
			preparestatement.setInt(1, membershipid);
			preparestatement.setInt(2, memberid);
			
			preparestatement.executeUpdate();
			c.commit();
			
			c.close();

		}catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
	}
	public boolean addmemberstodatabase(Member m)  {
		Connection c=connectionestablish();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean ifmemberexistsornot=checkmemberid(m.getMemberid(),c);
		if(ifmemberexistsornot) {
			try {
				c.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("memberid already exists please enter another memberid");
			return false;
		}
		
		String query="insert into gym.members values(?,?,?,null)";
		try {
			
			PreparedStatement  preparestatement=c.prepareStatement(query);
			preparestatement.setInt(1, m.getMemberid());
			preparestatement.setString(2, m.getName());
			preparestatement.setInt(3, m.getAge());
			
			
			boolean result=preparestatement.execute();
			if(!result) {
				
				System.out.println("added sucessfully");
			}
			else {
				System.out.println("not added sucessfylly");
			}
			c.commit();
			c.close();
		
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		return true;
	}
	public void deletemembershipidfromdatabase(int memberid)  {
		Connection c=connectionestablish();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean ifmemberexistsornot=checkmemberid(memberid,c);
		if(!ifmemberexistsornot) {
			try {
			c.rollback();}
			catch(SQLException e) {
				e.printStackTrace();
			}
			System.out.println("memberid not available");
			return;
		}
		
		
		String query="update gym.members SET membershipplanid=null  where memberid=?";
		try {
			
			PreparedStatement preparestatement=c.prepareStatement(query);
			preparestatement.setInt(1, memberid);
			preparestatement.execute();
			c.commit();
			c.close();
			
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
		
	}
	public void updatemembershipplan(int memberid,String planname,int duration,int fee)  {
		Connection c=connectionestablish();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		boolean ifmemberexistsornot=checkmemberid(memberid,c);
		if(!ifmemberexistsornot) {
			
			System.out.println("memberid not available");
			try {
				c.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		
		String query="Update  gym.members SET membershipplanid=? where memberid=?";
		int membershipplanid=getmembershipid(planname,duration,fee,c);
		if(membershipplanid==-1) {
			try {
				c.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("no such plan exists with given details");
			return;
		}
		System.out.println("membershipid "+membershipplanid);
		try {
			
			PreparedStatement preparestatement=c.prepareStatement(query);
			preparestatement.setInt(1, membershipplanid);
			preparestatement.setInt(2, memberid);
			
			preparestatement.execute();
			c.commit();
			c.close();
		} catch (SQLException e) {
			try {
			c.rollback();}catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}
	public void showmembersdb() {
		Connection c=connectionestablish();
		String query="select * from gym.members as member left join gym.membershipplan as memberplan on member.membershipplanid=memberplan.membershipplanid ";
		
		try {
			c.setAutoCommit(false);
			Statement statement=c.createStatement();
			ResultSet result=statement.executeQuery(query);
			while(result.next()) {
				System.out.println("Memberid:"+result.getInt("memberid"));
				System.out.println("Membername:"+result.getString("membername"));
				System.out.println("Memberage:"+result.getInt("memberage"));
				Integer membershipplanid = (Integer) result.getObject("membershipplanid");
				if(membershipplanid!=null) {
				System.out.println("planname:"+result.getString("membershipplanname"));
				System.out.println("planduration:"+result.getInt("membershipplanduration"));
				System.out.println("planfee:"+result.getInt("membershipplanfee"));
				}
				else {
					System.out.println("No plan assigned yet");
				}
			}
			c.commit();
			c.close();
			
			
		}
		catch(SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
	}

}
