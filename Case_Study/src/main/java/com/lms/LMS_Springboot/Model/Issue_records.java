package com.lms.LMS_Springboot.Model;

import java.sql.Date;

import org.springframework.stereotype.Component;


import com.lms.LMS_Springboot.Model.checking_enum.Status_issue;

import lombok.Data;


@Component 
@Data
public class Issue_records {
	private int issueid;
	private int memberid;
	private int bookid;
	private Date returndate;
	private  Date issuedate;
	
	private Status_issue status_issue;
	
}