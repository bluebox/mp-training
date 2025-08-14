package com.LMS.LibMS.service.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.LMS.LibMS.model.IssueRecord;

@Service
public interface IssueService {
	
	List<IssueRecord> findIssuedBooks();

}
