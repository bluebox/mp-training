package com.lms.springbootlms.service;

import com.lms.springbootlms.exception.ServiceException;
import com.lms.springbootlms.model.Book;
import com.lms.springbootlms.model.Member;

import java.util.List;
import java.util.Map;

public interface ReportService {

    List<Book> fetchOverdueBooks() throws ServiceException;

    Map<String, Long> fetchBookCountByCategory() throws ServiceException;

    List<Member> fetchMembersWithActiveIssues() throws ServiceException;
}
