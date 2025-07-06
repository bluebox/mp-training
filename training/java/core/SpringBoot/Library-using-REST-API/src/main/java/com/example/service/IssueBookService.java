package com.example.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.IssueRecord;
import com.example.repository.IssueRepository;

@Service
public class IssueBookService {
	@Autowired
	private IssueRepository repo;
	@Transactional
    public String issueBook(IssueRecord i) {
    	if(!repo.bookAvailability(i.getBookId())) {
        	return "Book not available";
        }
        if (!repo.checkMember(i.getMemberId())) {
            return "Member not found";
        }
        if (repo.bookIssued(i.getBookId())) {
            return "Book already issued";
        }
        if(repo.issueIdPresent(i.getIssueId())) {
        	return "Issue record with record id "+i.getIssueId()+" is already present";
        }
        if(repo.insertIssue(i)<=0) {
        	return "Issue Failed";
        }
        if (repo.updateAvaillability(i.getBookId()) > 0) {
            return "Book issued";
        } else {
            return "Book issue failed";
        }
    }
    public ArrayList<IssueRecord> showIssues() {
        ArrayList<IssueRecord> list = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "Practice", "Vbhanu@2003");
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM issue_records")) {
                while (rs.next()) {
                    list.add(new IssueRecord(rs.getInt(1),rs.getLong(2),rs.getInt(3),rs.getString(4).charAt(0)));
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
            try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bhanu", "practice", "Vbhanu@123")) {
                conn.setAutoCommit(false);
                long bookId=repo.findBook(issueId);
                if (bookId==-1) {
                    return "Issue record not found";
                }
                PreparedStatement ps2 = conn.prepareStatement("UPDATE issue_records SET statusrec = 'R' WHERE issueId = ?");
                ps2.setInt(1, issueId);
                int updatedStatus = ps2.executeUpdate();
                PreparedStatement ps3 = conn.prepareStatement("UPDATE books SET availability = 'A' WHERE bookId = ?");
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