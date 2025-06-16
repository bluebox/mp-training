package com.LibraryManagement.controller;

import com.LibraryManagement.services.impl.IssueRecordServiceImpl;

public class Library {
	public static void main(String[] args) throws Exception {
		IssueRecordServiceImpl ir=new IssueRecordServiceImpl();
		ir.issueBookToMember(1,1);
	}
}