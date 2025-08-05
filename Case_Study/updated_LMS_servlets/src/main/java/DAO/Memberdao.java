// File: dao/Memberdao.java
package DAO;

import domain.Book;
import domain.Member;
import domain.checking_enum.Availability;
import domain.checking_enum.Gender;
import domain.checking_enum.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Memberdao implements MemberInterface {
	
   	private final String URL="jdbc:mysql://127.0.0.1:3306/library_management_system";
	private final String USER="root";
	private final String PASSWORD="Santhosh@123";

    private Connection getConnection() throws SQLException {
    	try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Important line
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public boolean addMember(Member member) throws SQLIntegrityConstraintViolationException {
        String sql = "INSERT INTO members (Name, Email, Mobile, Gender, Address) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection()
        		
             ) {
        	conn.setAutoCommit(false);
        	PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, member.getGender().getType());
            stmt.setString(5, member.getAddress());

            int rows = stmt.executeUpdate();
            System.out.println(rows);
            if(rows>0) {
            	conn.commit();
            	return true;
            }
            else {
            	conn.rollback();
            	return false;
            }

        }
        catch(SQLIntegrityConstraintViolationException e) {
        	throw new SQLIntegrityConstraintViolationException();
        	
        }
        
        catch (SQLException e) {
        	
            e.printStackTrace();
            return false;
        }}
        


    @Override
    public boolean updateMember(Member member, int memberid) {
        String sql = "UPDATE members SET Name = ?, Email = ?, Mobile = ?, Gender = ?, Address = ? WHERE Memberid = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, member.getName());
            stmt.setString(2, member.getEmail());
            stmt.setLong(3, member.getMobile());
            stmt.setString(4, member.getGender().getType());
            stmt.setString(5, member.getAddress());
            stmt.setInt(6, memberid);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        String sql = "SELECT * FROM library_management_system.members;";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Member member = new Member(
                		rs.getInt("memberid"),
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getLong("Mobile"),
                        rs.getString("Address"),
                        Gender.getstatus(rs.getString("Gender"))
                );
                members.add(member);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(members);
        return members;
    }

    @Override
    public Member getById(int memberId) {
        String sql = "SELECT Name, Email, Mobile, Gender, Address FROM members WHERE Memberid = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Member(
                        rs.getString("Name"),
                        rs.getString("Email"),
                        rs.getLong("Mobile"),
                        rs.getString("Address"),
                        Gender.getstatus(rs.getString("Gender"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int getmemberid(Member member) {
    	Connection c=null;
		try {
			c = getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	String query="select memberid from library_management_system.members where name=? and mobile=? and email=?";
    	try {
    		PreparedStatement preparedstatement=c.prepareStatement(query);
    		preparedstatement.setString(1, member.getName());
    		preparedstatement.setLong(2, member.getMobile());
    		preparedstatement.setString(3, member.getEmail());

    		ResultSet res=preparedstatement.executeQuery();
    		if(res.next()) {
    			return res.getInt("memberid");
    		}
    		
    		
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
    	return -1;
    }
}
