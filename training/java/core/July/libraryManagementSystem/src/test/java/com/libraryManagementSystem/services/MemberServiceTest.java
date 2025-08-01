package com.libraryManagementSystem.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.libraryManagementSystem.domain.Member;
import com.libraryManagementSystem.exceptions.InvalidException;
import com.libraryManagementSystem.services.impl.MemberServiceImpl;
import com.libraryManagementSystem.utilities.MemberGender;

public class MemberServiceTest {

	private MemberService service;

	@Before
	public void setUp() throws InvalidException {

		service = new MemberServiceImpl();

	}

	@Test
	public void testRegisterMemberInvalidName() {
		try {
			service.registerMember(new Member(1, "n", "test@gmail.com", 1234567890, MemberGender.MALE, "madhapur"));
			fail("Expected InvalidException to be thrown");
		} catch (InvalidException e) {
			assertEquals("Name must be at least 3 characters long", e.getMessage());
		}
	}

	@Test
	public void testRegisterMemberInvalidEmail() {
		try {
			service.registerMember(new Member(1, "John", "invalid_email", 1234567890, MemberGender.MALE, "madhapur"));
			fail("Expected InvalidException to be thrown");
		} catch (InvalidException e) {
			assertEquals("Invalid email format", e.getMessage());
		}
	}

	@Test
	public void testRegisterMemberInvalidMobile() {
		try {
			service.registerMember(new Member(1, "John", "john@example.com", 123, MemberGender.MALE, "madhapur"));
			fail("Expected InvalidException to be thrown");
		} catch (InvalidException e) {
			assertEquals("Mobile number must be 10 digits", e.getMessage());
		}
	}

	@Test
	public void testRegisterMemberInvalidGender() {
		try {
			service.registerMember(new Member(1, "John", "john@email.com", 1234567890, null, "address"));
			fail("Expected InvalidException for gender");
		} catch (InvalidException e) {
			assertEquals("Gender must not be null", e.getMessage());
		}
	}

	@Test
	public void testRegisterMemberInvalidAddress() {
		try {
			service.registerMember(new Member(1, "John", "john@email.com", 1234567890, MemberGender.MALE, ""));
			fail("Expected InvalidException for address");
		} catch (InvalidException e) {
			assertEquals("Address must not be empty", e.getMessage());
		}
	}

	@Test
	public void testRegisterMemberDuplicateMobileNumber() {

		try {
			service.registerMember(new Member(1, "John", "john24@email.com", 1234567890, MemberGender.MALE, "address"));
			service.registerMember(new Member(2, "John", "john@email.com", 1234567890, MemberGender.MALE, "address"));
			fail("Expected InvalidException for duplicate mobile");
		} catch (InvalidException e) {
			assertEquals("Mobile Number Already Exist", e.getMessage());
		}
	}

	@Test
	public void testgetAllMembersValid() throws InvalidException {

		List<Member> result = service.getMembers();
		assertNotNull(result);
	}

	@Test
	public void testDeleteMemberInValid() throws InvalidException {
		try {
			service.deleteMember(new Member(1, "John", "john125@email.com", 9876343210L, MemberGender.MALE, "address"));
			fail("Expected InvalidException for No Member found");
		} catch (InvalidException e) {
			assertEquals("No Member found with Name: John", e.getMessage());

		}
	}

	@Test
	public void testDeleteMemberValid() throws InvalidException {
		try {
			int res = service.deleteMember(
					new Member(1, "John", "john125@email.com", 9876343210L, MemberGender.MALE, "address"));
			assertEquals(1, res);
		} catch (InvalidException e) {
			fail("Expected Delete Member Successful");
		}
	}

	@Test
	public void testRegisterMemberDuplicateEmail() {

		try {

			service.registerMember(new Member(1, "John", "john@email.com", 9876543210L, MemberGender.MALE, "address"));
			service.registerMember(new Member(2, "John", "john@email.com", 1234567892, MemberGender.MALE, "address"));
			fail("Expected InvalidException for duplicate email");
		} catch (InvalidException e) {
			assertEquals("Email Already Exist", e.getMessage());
		}
	}

	@Test
	public void testRegisterMemberValid() throws InvalidException {
		try {
			int res = service.registerMember(
					new Member(1, "John", "john125@email.com", 9876343210L, MemberGender.MALE, "address"));
			assertEquals(1, res);
		} catch (InvalidException e) {
			fail("Expected Member added Successful");
		}
	}

	@Test
	public void testUpdateMemberInvalidName() throws InvalidException {
		Member newMember = new Member(1, "nani", "test234@gmail.com", 1234567890, MemberGender.MALE, "madhapur");

		try {
			service.updateMember(new Member(1, "n", "test234@gmail.com", 1234567890, MemberGender.MALE, "madhapur"),
					newMember);
			fail("Expected InvalidException to be thrown");
		} catch (InvalidException e) {
			assertEquals("Name must be at least 3 characters long", e.getMessage());
		}
	}

	@Test
	public void testUpdateMemberInvalidEmail() throws InvalidException {
		Member newMember = new Member(1, "nani", "test234@gmail.com", 1234567890, MemberGender.MALE, "madhapur");

		try {
			service.updateMember(new Member(1, "nani", "test234", 1234567890, MemberGender.MALE, "madhapur"),
					newMember);
			fail("Expected InvalidException to be thrown");
		} catch (InvalidException e) {
			assertEquals("Invalid email format", e.getMessage());
		}
	}

	@Test
	public void testUpdateMemberInvalidMobile() throws InvalidException {
		Member newMember = new Member(1, "nani", "test234@gmail.com", 1234567890, MemberGender.MALE, "madhapur");

		try {
			service.updateMember(new Member(1, "nani", "test234@gmail.com", 1234567, MemberGender.MALE, "madhapur"),
					newMember);
			fail("Expected InvalidException to be thrown");
		} catch (InvalidException e) {
			assertEquals("Mobile number must be 10 digits", e.getMessage());
		}
	}

	@Test
	public void testUpdateMemberInvalidGender() throws InvalidException {
		Member newMember = new Member(1, "nani", "test234@gmail.com", 1234567890, MemberGender.MALE, "madhapur");

		try {
			service.updateMember(new Member(1, "nani", "test234@gmail.com", 1234567890, null, "madhapur"), newMember);
			fail("Expected InvalidException to be thrown");
		} catch (InvalidException e) {
			assertEquals("Gender must not be null", e.getMessage());
		}
	}

}