package com.lms.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import com.lms.Utils.DatabaseUtil;
import com.lms.dao.MemberDao;
import com.lms.dao.MemberDaoImpl;
import com.lms.model.Member;

public class TestMemberDaoImpl {

    private static final String url = DatabaseUtil.getUrl();
    private static final String user = DatabaseUtil.getUser();
    private static final String password = DatabaseUtil.getPassword();
    private MemberDao memberDao;

    @Before
    public void setUp() {
        memberDao = new MemberDaoImpl();
    }

    @Test
    public void testInsertMember() {
        String name = "Test User";
        String email = "testuser@example.com";
        String mobile = "8444389253";
        char gender = 'M';
        String address = "my Street";
        Member newMember = new Member(name, email, mobile, gender, address);
        int i=memberDao.insertMember(newMember);
        if(i!=0) {
        	int memberId = newMember.getMember_Id();
            String query = "SELECT * FROM members WHERE MemberId= ?";
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, memberId);
                ResultSet rs = stmt.executeQuery();
                assertTrue("Inserted member should exist in the DB", rs.next());
                assertEquals(name, rs.getString("Name"));
                assertEquals(email, rs.getString("Email"));
                assertEquals(mobile, rs.getString("Mobile"));
                assertEquals(String.valueOf(gender), rs.getString("Gender"));
                assertEquals(address, rs.getString("Address"));
            } catch (SQLException e) {
                fail("SQLException during test: " + e.getMessage());
            }
        }
        else {
        	assertEquals(0, i);
        }
        
    }

    @Test
    public void testFetchAllMembers() {
        String name = "Test Name";
        String email = "test@example.com";
        String mobile = "8445733254";
        char gender = 'F';
        String address = "my City";
        Member member = new Member(name, email, mobile, gender, address);
        int i=memberDao.insertMember(member);
        if(i!=0) {
        	List<Member> members = memberDao.fetchAllMembers();
            assertNotNull("Member list should not be null", members);
            assertTrue("Member list should contain at least one record", members.size() > 0);
            boolean flag = false;
            for (Member m : members) {
                if (m.getMember_Id() == member.getMember_Id()) {
                    flag = true;
                    assertEquals(name, m.getMember_Name());
                    assertEquals(email, m.getEmail());
                    assertEquals(mobile, m.getMobile_No());
                    assertEquals(gender, m.getGender());
                    assertEquals(address, m.getAddress());
                    break;
                }
            }
            assertTrue("Test member should be found in the fetched list", flag);
        }
        else {
        	assertEquals(0, i);
        }
    }

    @Test
    public void testUpdateMember() {
        String name = "my Name";
        String email = "me@example.com";
        String mobile = "8114577258";
        char gender = 'M';
        String address = "myCity";
        Member member = new Member(name, email, mobile, gender, address);
        int i=memberDao.insertMember(member);
        if(i!=0) {
        	memberDao.insertMember(member);
        	String updatedName = "other Name";
            String updatedEmail = "notme@example.com";
            String updatedMobile = "8111111257";
            char updatedGender = 'F';
            String updatedAddress = "other City";
            member.setMember_Name(updatedName);
            member.setEmail(updatedEmail);
            member.setMobile_No(updatedMobile);
            member.setGender(updatedGender);
            member.setAddress(updatedAddress);
            int j=memberDao.updateMember(member);
            if(j!=0) {
            	String query = "SELECT * FROM members WHERE MemberId = ?";
                try (Connection conn = DriverManager.getConnection(url, user, password);
                     PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, member.getMember_Id());
                    ResultSet rs = stmt.executeQuery();
                    assertTrue("Member should exist after update", rs.next());
                    assertEquals(updatedName, rs.getString("Name"));
                    assertEquals(updatedEmail, rs.getString("Email"));
                    assertEquals(updatedMobile, rs.getString("Mobile"));
                    assertEquals(updatedGender, rs.getString("Gender").charAt(0));
                    assertEquals(updatedAddress, rs.getString("Address"));
                } catch (SQLException e) {
                    e.printStackTrace();
                    fail("SQLException during updateBook test: " + e.getMessage());
                }
            }
            else {
            	assertEquals(0, j);
            }
        }
        else {
        	assertEquals(0, i);
        }
    }

    @Test
    public void testAddMemberLogs() {
        String name = "abc";
        String email = "abc@example.com";
        String mobile = "8222276255";
        char gender = 'M';
        String address = "123 Street";
        Member member = new Member(name, email, mobile, gender, address);
        int i=memberDao.insertMember(member);
        if(i!=0) {
        	String query = "SELECT * FROM members_log WHERE MemberId = ?";
            try (Connection conn = DriverManager.getConnection(url, user, password);
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, member.getMember_Id());
                ResultSet rs = stmt.executeQuery();
                assertTrue("Member log should exist after insertion", rs.next());
                assertEquals(member.getMember_Id(), rs.getInt("MemberId"));
                assertEquals(name, rs.getString("Name"));
                assertEquals(email, rs.getString("Email"));
                assertEquals(mobile, rs.getString("Mobile"));
                assertEquals(String.valueOf(gender), rs.getString("Gender"));
                assertEquals(address, rs.getString("Address"));
            } catch (SQLException e) {
                e.printStackTrace();
                fail("SQLException during testAddMemberLogs: " + e.getMessage());
            }
        }
        else {
        	assertEquals(0, i);
        }
    }

    @Test
    public void testGetMemberById() {
        String name = "xyz";
        String email = "xyz@example.com";
        String mobile = "8234562256";
        char gender = 'M';
        String address = "xyz street";
        Member member = new Member(name, email, mobile, gender, address);
        int i=memberDao.insertMember(member);
        if(i!=0) {
        	Member Resultedmember = memberDao.getMemberById(member.getMember_Id());
            assertNotNull("Member should be found", Resultedmember);
            assertEquals("Member ID should match", member.getMember_Id(), Resultedmember.getMember_Id());
            assertEquals("Name should match", name, Resultedmember.getMember_Name());
            assertEquals("Email should match", email, Resultedmember.getEmail());
            assertEquals("Mobile should match", mobile, Resultedmember.getMobile_No());
            assertEquals("Gender should match", gender, Resultedmember.getGender());
            assertEquals("Address should match", address, Resultedmember.getAddress());
        }
        else {
        	assertEquals(0,i);
        }
    }
}
