package com.example.service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.example.model.IssueRecord;

@Service
public class IssueBookService {
    public String issueBook(IssueRecord i) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "Practice", "Vbhanu@2003")) {
                conn.setAutoCommit(false);
                PreparedStatement ps1 = conn.prepareStatement("SELECT availabilty FROM books WHERE bookId = ?");
                ps1.setLong(1, i.getBookId());
                ResultSet rs1 = ps1.executeQuery();
                if (!rs1.next()) {
                    return "Book not found";
                }
                char availability = rs1.getString(1).charAt(0);
                if (availability != 'A') {
                    return "Book not available";
                }
                PreparedStatement ps2 = conn.prepareStatement("SELECT * FROM member WHERE memberId = ?");
                ps2.setInt(1, i.getMemberId());
                ResultSet rs2 = ps2.executeQuery();
                if (!rs2.next()) {
                    return "Member not found";
                }
                PreparedStatement ps3 = conn.prepareStatement("SELECT * FROM issuerecords WHERE bookId = ? AND statusrec = 'I'");
                ps3.setLong(1, i.getBookId());
                ResultSet rs3 = ps3.executeQuery();
                if (rs3.next()) {
                    return "Book already issued";
                }
                PreparedStatement ps4 = conn.prepareStatement("INSERT INTO issuerecords VALUES (?, ?, ?, ?, ?, ?)");
                ps4.setInt(1, i.getIssueId());
                ps4.setLong(2, i.getBookId());
                ps4.setInt(3, i.getMemberId());
                ps4.setString(4,Character.toString(i.getStatusrec()));
                ps4.setDate(5, Date.valueOf(i.getIssueDate()));
                ps4.setDate(6, Date.valueOf(i.getReturnDate()));
                ps4.executeUpdate();
                PreparedStatement ps5 = conn.prepareStatement("UPDATE books SET availabilty = 'N' WHERE bookId = ?");
                ps5.setLong(1, i.getBookId());
                int updated = ps5.executeUpdate();
                if (updated > 0) {
                    conn.commit();
                    return "Book issued";
                } else {
                    conn.rollback();
                    return "Book issue failed";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Issue failed";
    }
    public ArrayList<IssueRecord> showIssues() {
        ArrayList<IssueRecord> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "Practice", "Vbhanu@2003");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM issuerecords")) {
                while (rs.next()) {
                    list.add(new IssueRecord(
                            rs.getInt(1),
                            rs.getLong(2),
                            rs.getInt(3),
                            rs.getString(4).charAt(0)
                    ));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public String returnBook(int issueId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "Practice", "Vbhanu@2003")) {
                conn.setAutoCommit(false);
                PreparedStatement ps = conn.prepareStatement("SELECT bookId FROM issuerecords WHERE issueId = ?");
                ps.setInt(1, issueId);
                ResultSet rs = ps.executeQuery();
                long bookId;
                if (rs.next()) {
                    bookId = rs.getLong("bookId");
                } else {
                    return "Issue record not found";
                }
                PreparedStatement ps2 = conn.prepareStatement("UPDATE issuerecords SET statusrec = 'R' WHERE issueId = ?");
                ps2.setInt(1, issueId);
                int updatedStatus = ps2.executeUpdate();
                PreparedStatement ps3 = conn.prepareStatement("UPDATE books SET availabilty = 'A' WHERE bookId = ?");
                ps3.setLong(1, bookId);
                int updatedBook = ps3.executeUpdate();
                if (updatedStatus > 0 && updatedBook > 0) {
                    conn.commit();
                    return "Book returned";
                } else {
                    conn.rollback();
                    return "Return failed";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Return failed";
    }
}