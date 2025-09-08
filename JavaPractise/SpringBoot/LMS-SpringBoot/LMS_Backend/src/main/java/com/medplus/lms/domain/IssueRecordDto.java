package com.medplus.lms.domain;

import lombok.Data;

@Data
public class IssueRecordDto extends IssueRecord{
	private String bookTitle;
	private String memberName;
}
