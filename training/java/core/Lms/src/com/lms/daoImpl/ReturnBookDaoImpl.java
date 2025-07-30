package com.lms.daoImpl;

import com.lms.dao.ReturnBookDaoInterface;
import com.lms.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReturnBookDaoImpl implements ReturnBookDaoInterface {

    @Override
    public String fetchMemberName(String mobile) {
        String name = null;
        String query = "SELECT name FROM members WHERE mobile = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, mobile);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return name;
    }

    @Override
    public List<String> fetchIssuedBooks(String mobile) {
        List<String> books = new ArrayList<>();
        String query = """
            SELECT b.title 
            FROM books b
            JOIN issue_books ib ON b.book_id = ib.book_id
            JOIN members m ON m.member_id = ib.member_id
            WHERE m.mobile = ? AND ib.actual_return_date IS NULL
        """;

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, mobile);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                books.add(rs.getString("title"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }

    @Override
    public boolean updateBookReturnStatus(String mobile, String bookName, String status) {
        String getBookIdQuery = "SELECT book_id FROM books WHERE title = ?";
        String getMemberIdQuery = "SELECT member_id FROM members WHERE mobile = ?";
        String updateBookQuery = "UPDATE books SET status = ?, availability = 'A' WHERE book_id = ?";
        String updateIssueBookQuery = "UPDATE issue_books SET actual_return_date = CURDATE() WHERE book_id = ? AND member_id = ? AND actual_return_date IS NULL";

        try (Connection conn = DBUtil.getConnection()) {
            conn.setAutoCommit(false);

            String bookId = null;
            int memberId = -1;

            try (PreparedStatement ps = conn.prepareStatement(getBookIdQuery)) {
                ps.setString(1, bookName);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    bookId = rs.getString("book_id");
                }
            }

            if (bookId == null) {
                conn.rollback();
                return false;
            }

            try (PreparedStatement ps = conn.prepareStatement(getMemberIdQuery)) {
                ps.setString(1, mobile);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    memberId = rs.getInt("member_id");
                }
            }

            if (memberId == -1) {
                conn.rollback();
                return false;
            }

            try (PreparedStatement ps = conn.prepareStatement(updateBookQuery)) {
                ps.setString(1, "Active".equalsIgnoreCase(status) ? "A" : "I");
                ps.setString(2, bookId);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = conn.prepareStatement(updateIssueBookQuery)) {
                ps.setString(1, bookId);
                ps.setInt(2, memberId);
                ps.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
