package com.lms.service;



import com.lms.model.ReturnBook;
import java.util.List;

public interface ReturnBookService {
    String getMemberNameByMobile(String mobile);
    List<String> getIssuedBooksByMobile(String mobile);
    boolean returnBook(ReturnBook returnBook);
}
