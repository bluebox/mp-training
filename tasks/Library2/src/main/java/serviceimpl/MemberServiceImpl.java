package serviceimpl;

import model.Member;
import model.MemberIssueDTO;
import model.Book;
import model.IssueRecord;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import daoimpl.BookDAOImpl;
import daoimpl.IssueDAOImpl;
import daoimpl.MemberDAOImpl;
import service.MemberService;

public class MemberServiceImpl implements MemberService {

	private final MemberDAOImpl memberDAO = new MemberDAOImpl();
	private final BookDAOImpl bookDAO = new BookDAOImpl();
	private final IssueDAOImpl issueDAO = new IssueDAOImpl();

	public void addMember(Member member) throws Exception {
		if (member.getName() == null || member.getName().isBlank()) {
			throw new IllegalArgumentException("Name is required.");
		}
		if (member.getEmail() == null || member.getEmail().isBlank()) {
			throw new IllegalArgumentException("Email is required.");
		}
		if (!(member.getGender() == 'M' || member.getGender() == 'F')) {
			throw new IllegalArgumentException("Gender must be M or F.");
		}
		if (member.getAddress() == null || member.getAddress().isBlank()) {
			throw new IllegalArgumentException("Address is required.");
		}

		memberDAO.addMember(member);
	}

	public void updateMember(Member member) throws Exception {
		if (member.getMemberId() < 0) {
			throw new IllegalArgumentException("Invalid Member ID.");
		}
		if (member.getName() == null || member.getName().isBlank()) {
			throw new IllegalArgumentException("Name is required.");
		}
		if (member.getEmail() == null || member.getEmail().isBlank()) {
			throw new IllegalArgumentException("Email is required.");
		}
		if (!(member.getGender() == 'M' || member.getGender() == 'F')) {
			throw new IllegalArgumentException("Gender must be M or F.");
		}
		if (member.getAddress() == null || member.getAddress().isBlank()) {
			throw new IllegalArgumentException("Address is required.");
		}

		memberDAO.updateMember(member);
	}

	public List<Member> getAllMembers() throws Exception {
		return memberDAO.getAllMembers();
	}
	
	public List<MemberIssueDTO> getMembersWithActiveIssues() throws Exception {
	    List<Book> books = bookDAO.getAllBooks();
	    Map<Integer, Book> bookMap = books.stream()
	        .filter(book -> book.getStatus() == 'A' && book.getAvailability() == 'I')
	        .collect(Collectors.toMap(Book::getBookId, Function.identity()));

	    List<Member> members = memberDAO.getAllMembers();
	    Map<Integer, Member> memberMap = members.stream()
	        .collect(Collectors.toMap(Member::getMemberId, Function.identity()));

	    List<IssueRecord> issueRecords = issueDAO.getAllIssuedRecords();

	    List<MemberIssueDTO> result = issueRecords.stream()
	        .filter(record -> record.getStatus() == 'I')
	        .filter(record -> bookMap.containsKey(record.getBookId()))
	        .map(record -> {
	            Book book = bookMap.get(record.getBookId());
	            Member member = memberMap.get(record.getMemberId());
	            return new MemberIssueDTO(
	                member.getMemberId(),
	                member.getName(),
	                book.getBookId(),
	                book.getTitle(),
	                member.getMobile(),
	                member.getAddress(),
	                record.getIssueDate()
	            );
	        })
	        .collect(Collectors.toList());

	    return result;
	}

}
