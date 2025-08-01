package com.library_management.services;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.After;

import com.library_management.dao.implementation.MemberDaoImplementation;
import com.library_management.domain.Member;
import com.library_management.exceptions.DatabaseException;
import com.library_management.exceptions.InvalidDetailsException;
import com.library_management.services.implementation.MemberServiceImplementation;
import com.library_management.utilities.DBConnection;


public class MemberServiceImplementationTest {
	private MemberServiceImplementation service;
	private int generatedId;

    @Before
    public void setUp() throws DatabaseException, SQLException {
        service = new MemberServiceImplementation();
        MemberDaoImplementation dao=new MemberDaoImplementation();
        DBConnection.connectToDB("jdbc:mysql://localhost:3306/lms_test");
//        this.generatedId=dao.insertMember(new Member("test","test5638786890@gmail.com","9089624918","M","Seethampeta"));
    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateAddMember_WithEmptyEmail_ShouldThrowException() throws Exception {
        service.addMember(new Member("tarunaa","","9943989904","M","Hyderabad"));
    }

//    @Test(expected = InvalidDetailsException.class)
//    public void testValidateAddBook_WithNullCategory_ShouldThrowException() throws Exception {
//        service.validateAddBook("Love Story", "Rohit", null);
//    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateAddMember_WithTooLongName_ShouldThrowException() throws Exception {
    	StringBuilder sb=new StringBuilder("");
    	for(int i=1; i<=256; i++) {
    		sb.append("R");
    	}
        String overflowedTitle = sb.toString();
        service.addMember(new Member(overflowedTitle,"tarun123@gmail.com","9292929292","M","Hyderabad"));
    }

    @Test
    public void testValidateViewAllMembers_ShouldReturnList() throws Exception {
        assertNotNull(service.validateViewAllMembers());
    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateUpdateMemberDetails_WithEmptyFields_ShouldThrowException() throws Exception {
        service.validateUpdateMemberDetails(new Member(this.generatedId, "", "","","M","hyd"));
    }

    @Test(expected = InvalidDetailsException.class)
    public void testValidateUpdateMemberDetails_WithUnchangedData_ShouldThrowException() throws Exception {
        service.validateUpdateMemberDetails(new Member(this.generatedId, "test", "test@gmail.com","9191919191","M","vizag" ));
    }
//
//    @Test
//    public void testValidateUpdateMemberDetails_WithChanges_ShouldPass() throws Exception {
//        service.validateUpdateMemberDetails(new Member(this.generatedId,"ramulu", "varma@gmail.com", "9090809090","F","hyd"));
//    }
//    
//    @After
//    public void tearDown() throws Exception {
//    	MemberDaoImplementation dao=new MemberDaoImplementation();
//    	dao.deleteMember(this.generatedId);
//    	DBConnection.closeStatement();
//        DBConnection.closeConn();
//    }
}
