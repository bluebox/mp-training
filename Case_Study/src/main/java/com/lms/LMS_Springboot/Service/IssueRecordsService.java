package com.lms.LMS_Springboot.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.lms.LMS_Springboot.DAO.IssueRecordsDAO;
import com.lms.LMS_Springboot.Model.Issue_records;
@Component
@Service
public class IssueRecordsService {
	
	@Autowired
	private IssueRecordsDAO issuerecordsdao; 
	public boolean issuebook(int bookid,int memberid) {
		return issuerecordsdao.issueBook(bookid, memberid);
		
	}
	public boolean returnbook(int bookid,int memberid) {
		return issuerecordsdao.returnBook(bookid, memberid);
	}
	
	public List<Issue_records> viewallissuerecords(){
		return issuerecordsdao.printAllIssueRecords();
	}
	

}
