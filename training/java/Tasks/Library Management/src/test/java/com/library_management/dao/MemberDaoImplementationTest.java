package com.library_management.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.internal.runners.statements.Fail;

import com.library_management.dao.implementation.BookDaoImplementation;
import com.library_management.dao.implementation.MemberDaoImplementation;
import com.library_management.domain.Book;
import com.library_management.domain.BookAvailability;
import com.library_management.domain.BookCategory;
import com.library_management.domain.BookStatus;
import com.library_management.domain.Member;
import com.library_management.exceptions.DatabaseException;
import com.library_management.utilities.DBConnection;

public class MemberDaoImplementationTest {
    private MemberDaoImplementation dao;
    private int generatedId;

    @Before
    public void setUp() throws Exception {
    	DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms_test");
        dao=new MemberDaoImplementation();
        
//        this.generatedId=dao.insertMember(new Member("test", "test353442@gmail.com","9090909090","M","seeeyhas"));
    }

    @Test
    public void testAddMember_ShouldPass() throws DatabaseException, SQLException {
    	dao.insertMember(new Member("tarun","tarun67671@gmail.com","9907626081","M","seethampetys"));
    }

    
    @Test
    public void testUpdateMemberAvailability_ShouldPass() throws DatabaseException{
    	System.out.println(this.generatedId);
    	Member oldMember=new Member(generatedId, "test", "test121212@gmail.com","9292929212","F","Hyderabad");
    	dao.updateMember(oldMember, oldMember);
    }    
    @Test
    public void testSelectAllMembers_ShouldPass_ShouldReturnList() throws DatabaseException, SQLException{
    	Object result=dao.getAllMembers();
    	assertNotNull(result);
    	assertTrue(result instanceof List);
    }
    
////    Fail cAse
    @Test
    public void testSelectMemberById_ShouldPass_ShouldReturnNull() throws DatabaseException{
    	Object result=dao.selectMemberById(9999);
    	assertNull(result);
    }
//    
    @Test
    public void testDeleteMember_ShoulPass() throws DatabaseException{
    	Member oldMember=new Member(this.generatedId, "test", "test@gmail.com", "1231231233","M","hyderabad");
    }
//
//    @After
//    public void tearDown() throws Exception {
//    	dao.deleteMember(this.generatedId);
//    	DBConnection.closeStatement();
//        DBConnection.closeConn();
//    }

}
    