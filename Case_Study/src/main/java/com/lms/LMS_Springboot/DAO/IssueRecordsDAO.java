package com.lms.LMS_Springboot.DAO;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Issue_records;
import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Status;
import com.lms.LMS_Springboot.Model.checking_enum.Status_issue;
import com.lms.LMS_Springboot.Service.BookService;
import com.lms.LMS_Springboot.Service.MemberService;


@Repository
@Component
public class IssueRecordsDAO {
	
	
	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Autowired
	private MemberService memberservice;
	@Autowired
	private BookService bookservice;
	
	@Autowired
	private IssueRowMapper issuerowmapper;
   
	
	
    public boolean issueBook(int bookId, int memberId)  {
    	Book book=bookservice.bookwithid(bookId);
    	int res=0;
    	if(memberservice.getbyid(memberId)!=null && book.getAvailability()==Availability.AVAILABLE)
    	{
            String insertBookLogSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";
            String updateBookSql = "UPDATE books SET Availability = 'I' WHERE BookId = ?";
            String insertIssueSql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";

    		jdbctemplate.update(insertBookLogSql,book.getBookid(), book.getTitle(), book.getAuthor(),book.getCategory(),book.getStatus().getType(),book.getAvailability().getType());
    		jdbctemplate.update(updateBookSql,bookId);
    		 res=jdbctemplate.update(insertIssueSql,bookId,memberId, Date.valueOf(LocalDate.now()));
    		
    	}
    	if(res>0) {
    		return true;
    		
    	}
    	return false;

    }
    
    public Issue_records getIssueRecord(int bookId, int memberId)  {
        String sql = "SELECT IssueId,BookId,MemberId,Status,IssueDate,ReturnDate FROM issue_records WHERE BookId = ? AND MemberId = ? AND Status = 'I'";
        return jdbctemplate.queryForObject(sql, issuerowmapper,bookId,memberId);
        
        
    }
    public boolean returnBook(int bookId, int memberId)  {
    	int res=0;
    	Issue_records issuerecords=getIssueRecord(bookId,memberId);
    	if(issuerecords!=null && memberservice.getbyid(memberId)!=null) {
            String insertBookLogSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";
            String insertIssueLogSql = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, ?)";
            Book book=bookservice.bookwithid(bookId);
    		jdbctemplate.update(insertBookLogSql,book.getBookid(), book.getTitle(), book.getAuthor(),book.getCategory(),book.getStatus().getType(),book.getAvailability().getType());
   		 jdbctemplate.update(insertIssueLogSql,issuerecords.getIssueid(),bookId,memberId,book.getStatus().getType(),issuerecords.getIssuedate(), Date.valueOf(LocalDate.now()));
   		 
         String updateBookSql = "UPDATE books SET Availability = 'A' WHERE BookId = ? AND Availability = 'I'";
         String updateIssueSql = "UPDATE issue_records SET Status = 'R', ReturnDate = ? WHERE BookId = ? AND MemberId = ? AND Status = 'I'";
         jdbctemplate.update(updateBookSql,bookId);
         res=jdbctemplate.update(updateIssueSql,Date.valueOf(LocalDate.now()),bookId,memberId);
         
    	}
    	if(res>0) {
    		return true;
    	}
    	return false;
    	

    }
    
    
    public List<Issue_records> printAllIssueRecords()  {

        String sql = "SELECT issueid,bookid,memberid,status,issuedate,returndate FROM issue_records";
	    List<Issue_records> issuerecords = jdbctemplate.query(sql,issuerowmapper);

	    return issuerecords;
    	
    	
    }
    

    
    
    

}










@Component
@Scope("prototype")
class IssueRowMapper implements RowMapper<Issue_records> {
	@Autowired
	private Issue_records issuerecords;

	@Override
    public Issue_records mapRow(ResultSet rs, int rowNum) throws SQLException {
		Issue_records issuerecords=new Issue_records();
		issuerecords.setBookid(rs.getInt("bookid"));
		issuerecords.setIssueid(rs.getInt("issueid"));
		issuerecords.setMemberid(rs.getInt("memberid"));
		issuerecords.setIssuedate(rs.getDate("issuedate"));
		issuerecords.setReturndate(rs.getDate("returndate"));
		issuerecords.setStatus_issue(Status_issue.getstatus(rs.getString("status")));
        return issuerecords;
    	
}
}
