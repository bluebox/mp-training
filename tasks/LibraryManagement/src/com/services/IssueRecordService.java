package com.services;

import com.DAO.IssueRecordDAO;
import com.models.IssueRecord;
import java.sql.Date;

public class IssueRecordService {
    private final IssueRecordDAO dao = new IssueRecordDAO();

    public boolean issueBook(int bookId, int memberId) {
        Date today = new Date(System.currentTimeMillis());
        IssueRecord record = new IssueRecord(bookId, memberId, 'I', today, null);
        try {
        	if(dao.isBookAvailable(bookId))
            {
            	return dao.insert(record);        	
            }
            System.out.print("Not available");
            return false;
            
		} catch (Exception e) {
			
			return false;
		}

    }


    public boolean returnBook(int bookId, int memberId) {
        Date today = new Date(System.currentTimeMillis());
        return dao.returnBook(bookId, memberId, today);
    }
}