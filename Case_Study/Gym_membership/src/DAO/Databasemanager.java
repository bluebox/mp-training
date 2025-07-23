package DAO;

import Services.Member;
import Services.MembershipPlan;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class Databasemanager {
	private final String url="jdbc:mysql://127.0.0.1:3306/gym";
	private final String username="root";
	private final String password="Santhosh@123";
	public List<MembershipPlan> membershipplan=new ArrayList<>(); 
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
	public void addmemberplans(MembershipPlan membership) throws SQLIntegrityConstraintViolationException {
		
		Connection c=connectionestablish();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		String query="insert into gym.membershipplan(membershipplanname,membershipplanduration,membershipplanfee) values(?,?,?)";
		try {
			PreparedStatement preparedstatement=c.prepareStatement(query);
		
			preparedstatement.setString(1,membership.getPlanName());
			preparedstatement.setInt(2,membership.getDurationMonths());
			preparedstatement.setInt(3,membership.getFee());
			preparedstatement.execute();
			c.commit();
			
			
		}
		catch(SQLIntegrityConstraintViolationException e) {
			throw new SQLIntegrityConstraintViolationException();
		}
		catch (SQLException e) {
			try {
				c.rollback();
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public int getmembershipid(String planname,int duration,int fee,Connection c) {
		
		int id=-1;
		String querytofindmembershipid="select membershipplanid from gym.membershipplan where membershipplanname=? and membershipplanduration=? and membershipplanfee=?";
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
	public List<MembershipPlan> showplans() {
		Connection c=connectionestablish();
		String querytoshowplans="select * from gym.membershipplan";
		try {
			Statement statement=c.createStatement();
			ResultSet result=statement.executeQuery(querytoshowplans);
			while(result.next()) {
				this.membershipplan.add(new MembershipPlan(result.getString("membershipplanname"),result.getInt("membershipplanduration"),result.getInt("membershipplanfee"),result.getInt("membershipplanid")));			
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return this.membershipplan;
		
	}
	
	
	
	
	public boolean addmembership(int memberid,int membershipid)  {
		Connection c=connectionestablish();
		int res=0;
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String querytofindmembershipid="select membershipplanid from gym.members where memberid=?";
		
		
		String query="update gym.members  SET membershipplanid=? where memberid=?";
		try {
			
			
			PreparedStatement  preparestatement=c.prepareStatement(query);
			PreparedStatement  preparestatementtofindmembershipid=c.prepareStatement(querytofindmembershipid);
			preparestatementtofindmembershipid.setInt(1, memberid);
			
			ResultSet resformembershipid=preparestatementtofindmembershipid.executeQuery();
			if(resformembershipid.next()) {
			Integer membershipplanid = (Integer) resformembershipid.getObject("membershipplanid");
			if(membershipplanid!=null) {
				
				System.out.println("membershipplan already exists \n if you want to change please select (4) option to update membershipplan");
				return false;
			}
			}
			preparestatement.setInt(1, membershipid);
			preparestatement.setInt(2, memberid);
			
			res=preparestatement.executeUpdate();
			c.commit();
			
			

		}catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if(res==1) {
			return true;
		}
		return false;
	}
	public boolean addmemberstodatabase(Member m) throws SQLIntegrityConstraintViolationException{
		Connection c=connectionestablish();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			
		
		} 
		catch(SQLIntegrityConstraintViolationException e) {
			throw new SQLIntegrityConstraintViolationException();
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
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}
	public boolean deletemembershipidfromdatabase(int memberid)  {
		Connection c=connectionestablish();
		boolean res=false;
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String query="update gym.members SET membershipplanid=null  where memberid=?";
		try {
			
			PreparedStatement preparestatement=c.prepareStatement(query);
			preparestatement.setInt(1, memberid);
			res=preparestatement.execute();
			
			c.commit();
			
		} catch (SQLException e) {
			try {
				c.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
		
		
		
	}
	public void updatemembershipplan(int memberid,String planname,int duration,int fee)  {
		
		Connection c=connectionestablish();
		try {
			c.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String query="Update  gym.members SET membershipplanid=? where memberid=?";
		int membershipplanid=getmembershipid(planname,duration,fee,c);
		if(membershipplanid==-1) {
			
			System.out.println("no such plan exists with given details");
			return;
		}
		System.out.println("membershipid "+membershipplanid);
		try {
			
			PreparedStatement preparestatement=c.prepareStatement(query);
			preparestatement.setInt(1, membershipplanid);
			preparestatement.setInt(2, memberid);
			
			boolean res=preparestatement.execute();
			if(!res) {
				System.out.println("memberid not exists");
			}
			c.commit();
			
		} catch (SQLException e) {
			try {
			c.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	public List<Member> showmembersdb() throws SQLIntegrityConstraintViolationException {
		
		String query="select * from gym.members as member left join gym.membershipplan as memberplan on member.membershipplanid=memberplan.membershipplanid ";
		Connection c=connectionestablish();
		List<Member>  list=new ArrayList<>();
		try{
			
			Statement statement=c.createStatement();
			ResultSet result=statement.executeQuery(query);
			while(result.next()) {
				Member member=(new Member(result.getString("membername"),result.getInt("memberage"),result.getInt("memberid")));
				Integer membershipplanid = (Integer) result.getObject("membershipplanid");
				if(membershipplanid!=null){
					member.setMembershipPlan(new MembershipPlan(result.getString("membershipplanname"),result.getInt("membershipplanduration"),result.getInt("membershipplanfee"),result.getInt("membershipplanid")));
					
				}
				else {
					member.setMembershipPlan(null);
					
				}
				list.add(member);
			}
			
			
			
			
			
		}
		catch(SQLIntegrityConstraintViolationException e) {
			throw new SQLIntegrityConstraintViolationException();
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return list;
		
		
	}

}
