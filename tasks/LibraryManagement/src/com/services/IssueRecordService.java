package com.services;

import com.DAO.IssueRecordDAO;
import com.models.IssueRecord;
import java.sql.Date;

public class IssueRecordService {
    private final IssueRecordDAO dao = new IssueRecordDAO();

    public boolean issueBook(int bookId, int memberId) {
        
        if (bookId <= 0) {
            System.out.println("Invalid Book ID.");
            return false;
        }
        if (memberId <= 0) {
            System.out.println("Invalid Member ID.");
            return false;
        }

        Date today = new Date(System.currentTimeMillis());
        IssueRecord record = new IssueRecord(bookId, memberId, 'I', today, null);

        try {
            if (dao.isBookAvailable(bookId)) {
                return dao.insert(record);
            } else {
                System.out.println("Book is not available.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace(); // Good practice for debugging
            return false;
        }
    }



    public boolean returnBook(int bookId, int memberId) {
        
        if (bookId <= 0) {
            System.out.println("Invalid Book ID.");
            return false;
        }

        if (memberId <= 0) {
            System.out.println("Invalid Member ID.");
            return false;
        }

        Date today = new Date(System.currentTimeMillis());

        try {
            return dao.returnBook(bookId, memberId, today);
        } catch (Exception e) {
            e.printStackTrace(); // For debugging/logging
            return false;
        }
    }

}