package com.lms.LMS_Springboot.DAO;

import com.lms.LMS_Springboot.Model.Book;
import com.lms.LMS_Springboot.Model.Member;
import com.lms.LMS_Springboot.Model.Issue_records;
import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Gender;
import com.lms.LMS_Springboot.Model.checking_enum.Status;
import com.lms.LMS_Springboot.Model.checking_enum.Status_issue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Scope("prototype")
@Repository
public class Issue_RecordDAO {

    @Autowired
    private JdbcTemplate template;

    public boolean isMemberRegistered(int memberId) {
        String sql = "SELECT MemberId, Name, Email, Mobile, Gender, Address FROM members WHERE MemberId = ?";
        List<Member> members = template.query(sql, new MemberRowMapper(), memberId);
        return !members.isEmpty();
    }

    public boolean isBookStatusActive(int bookId) {
        String sql = "SELECT BookId, Title, Author, Category, Status, Availability FROM books WHERE BookId = ? AND Status = 'A'";
        List<Book> books = template.query(sql, new BookRowMapper(), bookId);
        return !books.isEmpty();
    }

    public boolean isBookAvailable(int bookId) {
        if (isBookStatusActive(bookId)) {
            String sql = "SELECT BookId, Title, Author, Category, Status, Availability FROM books WHERE BookId = ? AND Availability = 'A'";
            List<Book> books = template.query(sql, new BookRowMapper(), bookId);
            return !books.isEmpty();
        }
        return false;
    }

    public static final class MemberRowMapper implements RowMapper<Member> {
        @Override
        public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
            Member member = new Member();
            member.setMemberid(rs.getInt("memberid"));
            member.setName(rs.getString("name"));
            member.setEmail(rs.getString("email"));
            member.setMobile(rs.getString("mobile"));
            member.setGender(Gender.getstatus(rs.getString("gender")));
            member.setAddress(rs.getString("address"));
            return member;
        }
    }

    public static final class BookRowMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
            Book book = new Book();
            book.setBookid(rs.getInt("bookid"));
            book.setTitle(rs.getString("title"));
            book.setAuthor(rs.getString("author"));
            book.setCategory(rs.getString("category"));
            book.setStatus(Status.getstatus(rs.getString("status")));
            book.setAvailability(Availability.getstatus(rs.getString("availability")));
            return book;
        }
    }
    
    
    public boolean issueBook(int bookId, int memberId) {
        if (!isMemberRegistered(memberId) || !isBookAvailable(bookId)) {
            return false;
        }

        String selectBookSql = "SELECT BookId, Title, Author, Category, Status, Availability FROM books WHERE BookId = ?";
        String insertBookLogSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";
        String updateBookSql = "UPDATE books SET Availability = 'I' WHERE BookId = ?";
        String insertIssueSql = "INSERT INTO issue_records (BookId, MemberId, Status, IssueDate) VALUES (?, ?, 'I', ?)";

        Book book = template.queryForObject(selectBookSql, new BookRowMapper(), bookId);

        template.update(insertBookLogSql,
                book.getBookid(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus().name().substring(0, 1),
                book.getAvailability().name().substring(0, 1));

        int updated = template.update(updateBookSql, bookId);
        if (updated == 0) {
            return false;
        }

        int insertedIssue = template.update(insertIssueSql, bookId, memberId, Date.valueOf(LocalDate.now()));
        return insertedIssue > 0;
    }


    public boolean isBookIssued(int bookId, int memberId) {
        String sql = "SELECT * FROM issue_records WHERE BookId = ? AND MemberId = ? AND Status = 'I'";
        List<Issue_records> issued = template.query(sql, new IssueRecordsRowMapper(), bookId, memberId);
        return !issued.isEmpty();
    }
    

    public boolean returnBook(int bookId, int memberId) {
        if (!isBookIssued(bookId, memberId) || !isMemberRegistered(memberId)) {
            return false;
        }

        String selectBookSql = "SELECT BookId, Title, Author, Category, Status, Availability FROM books WHERE BookId = ?";
        String insertBookLogSql = "INSERT INTO books_log (BookId, Title, Author, Category, Status, Availability) VALUES (?, ?, ?, ?, ?, ?)";
        String updateBookSql = "UPDATE books SET Availability = 'A' WHERE BookId = ? AND Availability = 'I'";
        String selectIssueSql = "SELECT IssueId, BookId, MemberId, Status, IssueDate, ReturnDate FROM issue_records WHERE BookId = ? AND MemberId = ? AND Status = 'I'";
        String insertIssueLogSql = "INSERT INTO issue_records_log (IssueId, BookId, MemberId, Status, IssueDate, ReturnDate) VALUES (?, ?, ?, ?, ?, ?)";
        String updateIssueSql = "UPDATE issue_records SET Status = 'R', ReturnDate = ? WHERE BookId = ? AND MemberId = ? AND Status = 'I'";


        Book book = template.queryForObject(selectBookSql, new BookRowMapper(), bookId);

        template.update(insertBookLogSql,
                book.getBookid(),
                book.getTitle(),
                book.getAuthor(),
                book.getCategory(),
                book.getStatus().name().substring(0, 1),
                book.getAvailability().name().substring(0, 1));

        int updatedBook = template.update(updateBookSql, bookId);
        if (updatedBook == 0) {
            return false;
        }

        List<Issue_records> issueRecords = template.query(selectIssueSql, new IssueRecordsRowMapper(), bookId, memberId);

        if (issueRecords.isEmpty()) {
            return false;
        }

        Issue_records issueRecord = issueRecords.get(0); 

        template.update(insertIssueLogSql,
        		issueRecord.getIsssueid(),
                issueRecord.getBookid(),
                issueRecord.getMemberid(),
                issueRecord.getStatus_issue().name().substring(0, 1),
                issueRecord.getIssuedate(),
                issueRecord.getReturndate());

        int updatedIssue = template.update(updateIssueSql, Date.valueOf(LocalDate.now()), bookId, memberId);

        return updatedIssue > 0;
    }

    public static final class IssueRecordsRowMapper implements RowMapper<Issue_records> {
        @Override
        public Issue_records mapRow(ResultSet rs, int rowNum) throws SQLException{
            Issue_records issuereecord=new Issue_records();
            issuereecord.setIsssueid(rs.getInt("issueid"));
            issuereecord.setBookid( rs.getInt("bookid"));
            issuereecord.setMemberid( rs.getInt("memberid"));
            issuereecord.setStatus_issue(Status_issue.getstatus(rs.getString("status")));
            issuereecord.setIssuedate(rs.getDate("issuedate"));
            issuereecord.setReturndate(rs.getDate("returndate"));
            return issuereecord;
        }
    }
    public List<Issue_records> printAllIssueRecords() throws SQLException {
        String sql = "SELECT IssueId,BookId,MemberId,Status,IssueDate,ReturnDate FROM issue_records";

        List<Issue_records> issueRecords = template.query(sql, new IssueRecordsRowMapper());
       

          
               
               
        
        return issueRecords;
    }
    
    
    

 public List<Book> viewOverdueBooks() {
     String sql = """
         SELECT b.BookId, b.Title, b.Author, b.Category, b.Status, b.Availability
         FROM issue_records ir
         INNER JOIN books b ON b.BookId = ir.BookId
         WHERE DATEDIFF(CURRENT_DATE(), ir.IssueDate) > 10
            OR (ir.ReturnDate IS NOT NULL AND DATEDIFF(ir.ReturnDate, ir.IssueDate) > 10)
         """;

     return template.query(sql, new BookRowMapper());
 }

 public List<Member> viewMembersWithActiveIssues() {
     String sql = """
         SELECT DISTINCT m.MemberId, m.Name, m.Email, m.Mobile, m.Gender, m.Address
         FROM issue_records ir
         INNER JOIN members m ON m.MemberId = ir.MemberId
         WHERE ir.Status = 'I'
         """;

     return template.query(sql, new MemberRowMapper());
 }

   
}
