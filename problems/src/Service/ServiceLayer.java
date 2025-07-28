package Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.function.UnaryOperator;
import DAO.BookImplementation;
import DAO.IssueRecordImplementation;
import DAO.MemberDAOImpl;
import DAO.MemberInterface;
import Domain.Book;
import Domain.BookAvailability;
import Domain.BookStatus;
import Domain.IssueRecord;
import Domain.IssueStatus;
import Domain.Member;
import javafx.scene.control.TextFormatter;

public class ServiceLayer implements ServiceInterface{
    BookImplementation bookimplementation=new BookImplementation();
    MemberInterface memberDAO = new MemberDAOImpl();
    IssueRecordImplementation issueImplementation =new IssueRecordImplementation();
	
	@Override
	public void addBook(String Title,String Author,String category,BookStatus status,BookAvailability availability) throws SQLException {
	
		bookimplementation.AddBook(new Book(1,Title,Author,category,status,availability));
		
	}


	@Override
	public Book updateBookDetails(int id,String Title,String Author,String category,BookStatus status,BookAvailability availability) throws SQLException {
	Book book=bookimplementation.updateBookDetails(new Book(id,Title,Author,category,status,availability));
	if(book != null) {
		return book;
	}
	System.out.println("Book not found in books");
	return null;
	}

//	@Override
//	public Book updateAvailability(int id,String Title,String Author,String category,BookStatus status,BookAvailability availability) throws SQLException {
//		Book book= bookimplementation.updateAvailability(new Book(id,Title,Author,category,status,availability));
//			return book;
//	}

	@Override
	public List<Book> getBooks() throws SQLException {
	    List<Book> books=bookimplementation.getBooks();
	    if(books.size()==0) {
	    	System.out.println("Books yet need to be added,Sorry for the Inconvenience");
			return null;
	    }
	    return books;
	}

	@Override
	public Book getBookbyId(int BookId) throws SQLException {
		Book book= bookimplementation.getBookbyId(BookId);
		if(book != null) {
			return book;
		}
		System.out.println("Book not found in books");
		return null;
	}

	@Override
	public void createBookIssue(int BookId, int MemberId, IssueStatus status, LocalDate issueDate, LocalDate ReturnDate) throws SQLException {
		issueImplementation.createBookIssue(BookId, MemberId, status, issueDate, ReturnDate);
	}

	@Override
	public void returnBook(int BookId, int MemebrId) throws SQLException {
		issueImplementation.returnBook(BookId, MemebrId);
	}


//	public boolean checkBookIssue(int BookId, int MemberId) throws SQLException {
//		return issueImplementation.checkBookIssue(BookId, MemberId);
//	}

	@Override
	public List<IssueRecord> getAllIssueRecords() throws SQLException {
		 List<IssueRecord> issues=issueImplementation.getAllIssueRecords();
		    if(issues.size()==0) {
		    	System.out.println("No issues yet");
				return null;
		    }
		    return issues;
	}
	
	
	 @Override
	    public void registerMember(Member member) throws Exception {
	        if (member.getName() == null || member.getName().isEmpty()) {
	            throw new Exception("Name cannot be empty.");
	        }
	        if (!member.getEmail().contains("@")) {
	            throw new Exception("Invalid email format.");
	        }
	        if(!member.getMobile().matches("\\d")){
	        	 throw new Exception("Invalid number format. should contain all decimal values");
	        }
	        memberDAO.addMember(member);
	    }

	    @Override
	    public Member updateMember(Member member) throws Exception {
	        Member existing = memberDAO.getMemberById(member.getId());
	        if (existing == null) {
	            throw new Exception("Member with ID " + member.getId() + " not found.");
	        }
	        return memberDAO.updateMember(member);
	    }

	    @Override
	    public List<Member> getAllMembers() throws Exception {
	        return memberDAO.getAllMembers();
	    }

	    @Override
	    public Member getMemberById(int memberId) throws Exception {
	        return memberDAO.getMemberById(memberId);
	    }
	  

	   

	    @Override
	    public List<Book> getOverdueBooks() throws Exception {
	        LocalDate today = LocalDate.now();

	        return issueImplementation.getAllIssueRecords().stream()
	            .filter(record -> record.getStatus() == IssueStatus.ISSUED)
	            .filter(record -> record.getIssueDate().plusDays(14).isBefore(today))
	            .map(record -> {
	                try {
	                    return bookimplementation.getBookbyId(record.getBookId());
	                } catch (Exception e) {
	                    e.printStackTrace();
	                    return null;
	                }
	            })
	            .filter(Objects::nonNull)
	            .collect(Collectors.toList());
	  

	    }

	    

	    

	    @Override
	    public Map<String, Long> getBookCountByCategory() throws Exception {
	        List<Book> books = getBooks();

	        return books.stream()
	                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
	    }

	    @Override
	    public List<Member> getMembersWithActiveIssuedBooks() throws Exception {
	        List<IssueRecord> records = getAllIssueRecords();

	        Set<Integer> memberIdsWithIssuedBooks = records.stream()
	                .filter(r -> r.getStatus() == IssueStatus.ISSUED)
	                .map(IssueRecord::getMemberId)
	                .collect(Collectors.toSet());

	        List<Member> allMembers = memberDAO.getAllMembers();

	        return allMembers.stream()
	                .filter(m -> memberIdsWithIssuedBooks.contains(m.getId()))
	                .collect(Collectors.toList());
	
	
	
	
	
}

	    @Override
	    public void deleteMember(int memberId) throws Exception {
	        Member existing = memberDAO.getMemberById(memberId);
	        if (existing == null) {
	            throw new Exception("Member with ID " + memberId + " not found.");
	        }
	        memberDAO.deleteMember(memberId);
	    }
	    public static void main(String[] args) throws Exception {
	    	ServiceLayer s=new ServiceLayer();
	    	System.out.println(s.getAllIssueRecords());
	    	
	    	System.out.println(s.getMembersWithActiveIssuedBooks());
	    }
	   
}




	
	
	
	
	
	