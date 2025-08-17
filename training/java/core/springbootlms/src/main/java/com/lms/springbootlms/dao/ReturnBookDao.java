package com.lms.springbootlms.dao;

import java.util.List;

import com.lms.springbootlms.exception.DaoException;

public interface ReturnBookDao {
    String fetchMemberName(String mobile)throws DaoException;

    List<String> fetchIssuedBooks(String mobile)throws DaoException;

    boolean updateBookReturnStatus(String mobile, String bookName, String status)throws DaoException;
}
