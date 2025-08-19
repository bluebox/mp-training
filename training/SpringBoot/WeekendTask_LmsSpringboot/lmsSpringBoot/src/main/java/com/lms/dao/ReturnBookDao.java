package com.lms.dao;



import com.lms.model.ReturnBook;
import java.util.List;

public interface ReturnBookDao {
    String fetchMemberName(String mobile);
    List<String> fetchIssuedBooks(String mobile);
    boolean updateBookReturnStatus(ReturnBook returnBook);
}
