package com.example.library.serviceimpl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.library.daoimpl.BookDAOImpl;
import com.example.library.daoimpl.IssueDAOImpl;
import com.example.library.daoimpl.MemberDAOImpl;
import com.example.library.model.Book;
import com.example.library.model.IssueRecord;
import com.example.library.model.Member;
import com.example.library.model.MemberIssueDTO;
import com.example.library.service.MemberService;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MemberServiceImpl implements MemberService {

	private final MemberDAOImpl memberDAO;
	private final BookDAOImpl bookDAO;
	private final IssueDAOImpl issueDAO;

	@Autowired
	public MemberServiceImpl(MemberDAOImpl memberDAO, BookDAOImpl bookDAO, IssueDAOImpl issueDAO) {
		this.memberDAO = memberDAO;
		this.bookDAO = bookDAO;
		this.issueDAO = issueDAO;
	}

	@Override
	public void addMember(Member member) throws Exception {
		if (member.getName() == null || member.getName().isEmpty()) {
			throw new IllegalArgumentException("Name is required.");
		}
		if (member.getEmail() == null || member.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email is required.");
		}
		if (!(member.getGender() == 'M' || member.getGender() == 'F')) {
			throw new IllegalArgumentException("Gender must be M or F.");
		}
		if (member.getAddress() == null || member.getAddress().isEmpty()) {
			throw new IllegalArgumentException("Address is required.");
		}

		memberDAO.addMember(member);
	}

	@Override
	public void updateMember(Member member) throws Exception {
		if (member.getMemberId() < 0) {
			throw new IllegalArgumentException("Invalid Member ID.");
		}
		if (member.getName() == null || member.getName().isEmpty()) {
			throw new IllegalArgumentException("Name is required.");
		}
		if (member.getEmail() == null || member.getEmail().isEmpty()) {
			throw new IllegalArgumentException("Email is required.");
		}
		if (!(member.getGender() == 'M' || member.getGender() == 'F')) {
			throw new IllegalArgumentException("Gender must be M or F.");
		}
		if (member.getAddress() == null || member.getAddress().isEmpty()) {
			throw new IllegalArgumentException("Address is required.");
		}

		memberDAO.updateMember(member);
	}

	@Override
	public List<Member> getAllMembers() throws Exception {
		return memberDAO.getAllMembers();
	}
	
	@Override
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
