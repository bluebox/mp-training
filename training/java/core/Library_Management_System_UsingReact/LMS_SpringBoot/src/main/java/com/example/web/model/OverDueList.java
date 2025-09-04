package com.example.web.model;

import java.sql.Date;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OverDueList {
	private int issueId;
	private int bookId;
	private String title;
	private String memberName;
	private Date overDueDate;
	
}
