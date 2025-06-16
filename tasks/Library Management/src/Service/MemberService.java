package Service;
import casestudy.Book; import casestudy.LibraryException;
import casestudy.Member;

import java.util.List; import java.util.Map; import java.util.stream.Collectors;

import DAO.BookDAO;
import DAO.MemberDAO;


public class MemberService { 
	
	private final MemberDAO memberDAO = new MemberDAO();
	public void addMember(Member member) throws LibraryException {
    if (member.getName() == null || member.getName().isEmpty()) {
        throw new LibraryException("Member name cannot be empty");
    }
    if (member.getEmail() == null || !member.getEmail().matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        throw new LibraryException("Invalid email format");
    }
    memberDAO.addMember(member);
}

public void updateMember(Member member) throws LibraryException {
    if (member.getMemberId() <= 0) {
        throw new LibraryException("Invalid member ID");
    }
    memberDAO.updateMember(member);
}

public List<Member> getAllMembers() throws LibraryException {
    return memberDAO.getAllMembers();
}}