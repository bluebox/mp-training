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

    @Override
    public boolean returnBook(int issueId, LocalDate actualReturnDate) {
        String query = "UPDATE issue_books SET actual_return_date = ? WHERE issue_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setDate(1, Date.valueOf(actualReturnDate));
            stmt.setInt(2, issueId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<IssueBook> getAllIssueRecords() {
        List<IssueBook> list = new ArrayList<>();
        String query = "SELECT * FROM issue_books";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<IssueBook> getActiveIssuesByMember(int memberId) {
        List<IssueBook> list = new ArrayList<>();
        String query = "SELECT * FROM issue_books WHERE member_id = ? AND actual_return_date IS NULL";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean isBookAlreadyIssued(String bookId) {
        String query = "SELECT COUNT(*) FROM issue_books WHERE book_id = ? AND actual_return_date IS NULL";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, bookId);
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
        String query = "SELECT book_id, title, author, category, status, availability " +
					   "FROM books WHERE category = ? AND availability = 'A'";

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

    public static Member getMemberByMobile(String mobile) throws InvalidInputException {
        String sql = "SELECT member_id, name, email, mobile,gender,address FROM members WHERE mobile = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

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

        } catch (SQLException e) {
            throw new InvalidInputException("Database connection issue");
        }
    }

    public void updateBookAvailability(String bookId, char availability) {
        String sql = "UPDATE books SET availability = ? WHERE book_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, String.valueOf(availability));
            stmt.setString(2, bookId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
