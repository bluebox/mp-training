
package com.lms.daoImpl;

import com.lms.dao.BookIssueDaoInterface;
import com.lms.model.Book;
import com.lms.model.BookCategory;
import com.lms.model.IssueBook;
import com.lms.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueBookDaoImpl implements BookIssueDaoInterface {
    public boolean issueBook(IssueBook record) {
        String query = "INSERT INTO issue_books (member_id, book_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, record.getMemberId());
            stmt.setString(2, record.getBookId());
            stmt.setDate(3, Date.valueOf(record.getIssueDate()));
            stmt.setDate(4, Date.valueOf(record.getReturnDate()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public boolean returnBook(int issueId, LocalDate returnDate) {
        String query = "UPDATE issue_books SET return_date = ? WHERE issue_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(returnDate));
            stmt.setInt(2, issueId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public List<IssueBook> getAllIssueRecords() {
        List<IssueBook> list = new ArrayList<>();
        String query = "SELECT * FROM issue_books";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(new IssueBook(
                        rs.getInt("issue_id"),
                        rs.getString("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public List<IssueBook> getActiveIssuesByMember(int memberId) {
        List<IssueBook> list = new ArrayList<>();
        String query = "SELECT * FROM issue_books WHERE member_id = ? AND return_date > CURRENT_DATE";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, memberId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(new IssueBook(
                        rs.getInt("issue_id"),
                        rs.getString("book_id"),
                        rs.getInt("member_id"),
                        rs.getDate("issue_date").toLocalDate(),
                        rs.getDate("return_date").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public boolean isBookAlreadyIssued(int bookId) {
        String query = "SELECT COUNT(*) FROM issue_books WHERE book_id = ? AND return_date > CURRENT_DATE";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, bookId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Book> getAvailableBooksByCategory(String category) {
        List<Book> availableBooks = new ArrayList<>();
        String query = "SELECT * FROM books WHERE category = ? AND availability = 'Y'";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return availableBooks;
    }

}
