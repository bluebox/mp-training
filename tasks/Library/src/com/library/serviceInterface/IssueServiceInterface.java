package com.library.serviceInterface;
import java.sql.Connection;
import java.util.List;

import com.library.domain.IssueRecord;

public interface IssueServiceInterface {

	public abstract void issueBook(int bookId, int memberId) throws Exception;
    
    public abstract  List<IssueRecord> getAllIssuedRecords() throws Exception;
    
    public abstract void updateIssueRecord(Connection conn)throws Exception;
    
    public abstract void returnBook(int bookId) throws Exception;

}
