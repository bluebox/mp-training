package com.lms.springbootlms.service;

import java.util.List;

import com.lms.springbootlms.exception.ServiceException;

public interface ReturnBookServiceInterface {

    String getMemberNameByMobile(String mobile) throws ServiceException;

    List<String> getIssuedBooksByMobile(String mobile) throws ServiceException;

    boolean returnBook(String mobile, String bookName, String status) throws ServiceException;
}
