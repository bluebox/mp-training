package com.lms.daoImpl;

import com.lms.dao.BookIssueDaoInterface;
import com.lms.exceptions.InvalidInputException;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.IssueBook;
import com.lms.model.Member;
import com.lms.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueBookDaoImpl implements BookIssueDaoInterface {

    @Override
    public boolean issueBook(IssueBook record) throws SQLException {
        String query = "INSERT INTO issue_books (member_id, book_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, record.getMemberId());
            stmt.setString(2, record.getBookId());
            stmt.setDate(3, Date.valueOf(record.getIssueDate()));
            stmt.setDate(4, Date.valueOf(record.getReturnDate())); 
      	  int rowsAffected = stmt.executeUpdate();
      	  		if (rowsAffected > 0) {
			updateBookAvailability(record.getBookId(), 'U'); 
			return true;
			} else {
				return false;
			}
    }

    @Override
    public boolean returnBook(int issueId, LocalDate actualReturnDate) throws SQLException {
        String query = "UPDATE issue_books SET actual_return_date = ? WHERE issue_id = ?";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setDate(1, Date.valueOf(actualReturnDate));
            stmt.setInt(2, issueId);
            return stmt.executeUpdate() > 0;
       
    }

    @Override
    public List<IssueBook> getAllIssueRecords() throws SQLException {
        List<IssueBook> list = new ArrayList<>();
        String query = "SELECT * FROM issue_books";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate actualReturn = rs.getDate("actual_return_date") != null
                        ? rs.getDate("actual_return_date").toLocalDate()
                        : null;

                list.add(new IssueBook(
                        rs.getInt("issue_id"),
                        rs.getString("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate(),
                        actualReturn
                ));
            }
			return list;
        }
       
    

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) throws SQLException {
        List<IssueBook> list = new ArrayList<>();
        String query = "SELECT * FROM issue_books WHERE member_id = ? AND actual_return_date IS NULL";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                LocalDate actualReturn = rs.getDate("actual_return_date") != null
                        ? rs.getDate("actual_return_date").toLocalDate()
                        : null;

                list.add(new IssueBook(
                        rs.getInt("issue_id"),
                        rs.getString("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate(),
                        actualReturn
                ));
            }
        
        return list;
    }

    
    public boolean isBookAlreadyIssued(String bookId) throws SQLException {
        String query = "SELECT COUNT(*) FROM issue_books WHERE book_id = ? AND actual_return_date IS NULL";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
       
        return false;
    }


    @Override
    public List<Book> getAvailableBooksByCategory(String category) throws SQLException {
        List<Book> availableBooks = new ArrayList<>();
        String query = "SELECT book_id, title, author, category, status, availability " +
					   "FROM books WHERE category = ? AND availability = 'A'";

        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, category);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setBookId(rs.getString("book_id"));
                book.setBookTitle(rs.getString("title"));
                book.setBookAuthor(rs.getString("author"));
                book.setBookCategory(BookCategory.valueOf(rs.getString("category")));
                book.setStatus(rs.getString("status").charAt(0));
                book.setAvailability(rs.getString("availability").charAt(0));
                availableBooks.add(book);
            }
       
        return availableBooks;
    }

    public Member getMemberByMobile(String mobile) throws InvalidInputException, SQLException {
        String sql = "SELECT member_id, name, email, mobile,gender,address FROM members WHERE mobile = ?";

        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, mobile);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Member(
                        rs.getInt("member_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("mobile"),
                        rs.getString("gender"),
                        rs.getString("address")
                );
            }
            throw new InvalidInputException("No member registered with this mobile number");

        
    }

    public void updateBookAvailability(String bookId, char availability) throws SQLException {
        String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
        Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, String.valueOf(availability));
            stmt.setString(2, bookId);
            stmt.executeUpdate();
        
    }
}
