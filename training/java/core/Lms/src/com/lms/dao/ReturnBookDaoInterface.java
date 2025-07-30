package com.lms.dao;


import java.util.List;

public interface ReturnBookDaoInterface {
    String fetchMemberName(String mobile);
    List<String> fetchIssuedBooks(String mobile);
    boolean updateBookReturnStatus(String mobile, String bookName, String status);
}
