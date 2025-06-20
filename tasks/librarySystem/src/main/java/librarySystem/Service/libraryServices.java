package librarySystem.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import librarySystem.DAO.*;
import model.*;

public class libraryServices {
	private BookDao bookDao = new BookDao();
	private MemberDao memberDao = new MemberDao();
	private IssueRecordDao issueDao = new IssueRecordDao();
	
	//books management
	public boolean addBook(BookPojo book) {
		if(book == null|| book.getTitle().isEmpty()||book.getAuthor().isEmpty()) {
			return false;
		}
		
//		if(bookDao.isPresent(book))
//		{
//			return false;
//		}
		
		return bookDao.insertBook(book);
	}
	
	public boolean updateBookDetails(BookPojo oldBook, BookPojo newBook) {
        if (oldBook == null || newBook == null) return false;
        return bookDao.updateBookDetails(oldBook, newBook);
    }
	
	public boolean updateBookAvailability(int bookId,String bookName, String Author,char availability) {
		BookPojo bookAuth = new BookPojo();
		bookAuth.setAuthor(Author);
		bookAuth.setTitle(bookName);
		if(bookId > 0)
		{
			if(!bookName.isEmpty() && !Author.isEmpty())
			{
				if(bookId != bookDao.getBook(bookAuth).getBookId())
				{
					return false;
				}else
				{
					return bookDao.updateBookAvailability(bookDao.getBook(bookAuth).getBookId(),availability);
				}
			}else
			{
				BookPojo book = bookDao.getBookById(bookId);
		        if (book == null) return false;
		        book.setAvailability(availability);
		        return bookDao.updateBookAvailability(book.getBookId(),availability);
			}
		}else
		{
			if(!bookName.isEmpty() && !Author.isEmpty())
			{
				return bookDao.updateBookAvailability(bookDao.getBook(bookAuth).getBookId(),availability);
			}else
			{
				return false;
			}
			
		}
        
    }
	
	public List<BookPojo> viewAllBooks() {
        return bookDao.getBooks();
    }
	
	//member
	public boolean registerMember(MemberPojo member) {
        if (member == null || member.getName().isEmpty()) return false;
        if (memberDao.isPresent(member)) {
            System.out.println("Member already exists.");
            return false;
        }
        return memberDao.registerMember(member);
    }

    public boolean updateMember(MemberPojo oldMember, MemberPojo newMember) {
        if (oldMember == null || newMember == null) return false;
        
        MemberPojo current = memberDao.getMemberByEmailOrMobile(oldMember.getEmail(), oldMember.getMobile());
        if (current == null) {
            System.out.println("Member not found for update.");
            return false;
        }

        
        return memberDao.updateMember(oldMember, newMember);
    }
    public boolean deleteMember(int id)
    {
    	return memberDao.deleteMember(id);
    }

    public List<MemberPojo> viewAllMembers() {
        return memberDao.getMembers();
    }
	
	//Issue
    
    public boolean issueBook(int bookId, int memberId, Date issueDate) {
        BookPojo book = bookDao.getBookById(bookId);
        if (book == null || book.getAvailability() == 'I') return false;

        IssueRecordPojo issue = new IssueRecordPojo();
        issue.setBookId(bookId);
        issue.setMemberId(memberId);
        issue.setIssueDate(issueDate);
        issue.setStatus('I');

        boolean success = issueDao.issueBook(issue);
        if (success) {
            book.setAvailability('I');
            return bookDao.updateBookAvailability(book.getBookId(),book.getAvailability());
        }
        return false;
    }

    public boolean returnBook(int bookId, int memberId, Date returnDate) {
        boolean returned = issueDao.returnBook(bookId, memberId, returnDate);
        if (returned) {
            BookPojo book = bookDao.getBookById(bookId);
            book.setAvailability('A');
            return bookDao.updateBookAvailability(book.getBookId(),book.getAvailability());
        }
        return false;
    }
    
    //analysis
    
    public List<IssueRecordPojo> getOverdueBooks(int limit) {
        List<IssueRecordPojo> records = issueDao.getAllRecords();
        LocalDate now = LocalDate.now();
        return records.stream()
                .filter(i -> i.getStatus() == 'I')
                .filter(i -> i.getIssueDate().toLocalDate().plusDays(limit).isBefore(now))
                .collect(Collectors.toList());
    }

    public Map<String, Long> getBookCountPerCategory() {
        return bookDao.getBooks().stream()
                .collect(Collectors.groupingBy(BookPojo::getCategory, Collectors.counting()));
    }

    public List<MemberPojo> getMembersWithActiveIssues() {
        List<IssueRecordPojo> records = issueDao.getAllRecords().stream()
                .filter(i -> i.getStatus() == 'I')
                .collect(Collectors.toList());
        Set<Integer> memberIds = records.stream()
                .map(IssueRecordPojo::getMemberId)
                .collect(Collectors.toSet());

        return memberDao.getMembers().stream()
                .filter(m -> memberIds.contains(m.getMemberId()))
                .collect(Collectors.toList());
    }
    
    public boolean deleteBook(int id)
    {
    	return bookDao.deleteBookById(id);
    }
    
    public List<IssueRecordPojo> getAllRecords()
    {
        return issueDao.getAllRecords();
    }
    
}
