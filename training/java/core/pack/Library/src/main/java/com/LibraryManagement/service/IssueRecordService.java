package Library.src.main.java.com.LibraryManagement.service;
import Library.src.main.java.com.LibraryManagement.model.*;
import java.util.*;

public interface IssueRecordService {
    void issueBook(int bookId, int memberId) throws Exception;
    void returnBook(int bookId, int memberId) throws Exception;
    List<IssueRecord> getAllIssueRecords() throws Exception;
    List<IssueRecord> getActiveIssuesByMember(int memberId) throws Exception;
    List<IssueRecord> getOverdueBooks() throws Exception;
}
