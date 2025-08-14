package com.LMS.LibMS.service.interfaceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LMS.LibMS.model.IssueRecord;
import com.LMS.LibMS.repository.IssueRepository;
import com.LMS.LibMS.service.interfaces.IssueService;

@Service
public class IssueServiceImpl implements IssueService{
	
	private IssueRepository issueRepository;
	
	@Autowired
	public IssueServiceImpl(IssueRepository issueRepository) {
		this.issueRepository=issueRepository;
	}
	
	@Override
	public List<IssueRecord> findIssuedBooks(){
		return issueRepository.findIssuedBooks();
	}

}
