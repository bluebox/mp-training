package com.LMS.LibMS.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.LMS.LibMS.model.IssueRecord;

@Repository
public interface IssueRepository {
	
	 List<IssueRecord> findIssuedBooks();
	
}
