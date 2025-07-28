package Service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import Domain.Book;
import Domain.BookAvailability;
import Domain.BookStatus;
import Domain.IssueRecord;
import Domain.IssueStatus;
import Domain.Member;

public interface ServiceInterface {
      void addBook(String Title,String Author,String category,BookStatus status,BookAvailability availability) throws SQLException;
      Book updateBookDetails(int id,String Title,String Author,String category,BookStatus status,BookAvailability availability) throws SQLException;
      //Book updateAvailability(int id,String Title,String Author,String category,BookStatus status,BookAvailability availability) throws SQLException;
      List<Book> getBooks() throws SQLException;
      Book getBookbyId(int BookId) throws SQLException;
      void createBookIssue(int BookId,  int MemberId,IssueStatus status, LocalDate issueDate,LocalDate ReturnDate) throws SQLException;
      void returnBook(int BookId,int MemebrId) throws SQLException ;
     // boolean checkBookIssue(int BookId,int MemberId) throws SQLException;
      List<IssueRecord> getAllIssueRecords() throws SQLException;
	  void registerMember(Member member) throws Exception;
	  Member updateMember(Member member) throws Exception;
	  List<Member> getAllMembers() throws Exception;
	  Member getMemberById(int memberId) throws Exception;
	  List<Book> getOverdueBooks() throws Exception;
	  Map<String, Long> getBookCountByCategory() throws Exception;
	  List<Member> getMembersWithActiveIssuedBooks() throws Exception;
	  void deleteMember(int memberId) throws Exception;
      
}
