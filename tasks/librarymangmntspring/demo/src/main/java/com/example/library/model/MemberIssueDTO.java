package com.example.library.model;

import java.time.LocalDate;

public class MemberIssueDTO {
    private int memberId;
    private String memberName;
    private int bookId;
    private String bookName;
    private long mobile;
    private String address;
    private LocalDate issueDate;

    public MemberIssueDTO(int memberId, String memberName, int bookId, 
    		String bookName, long mobile, String address, LocalDate issueDate) {
        this.memberId = memberId;
        this.memberName = memberName;
        this.bookId = bookId;
        this.bookName = bookName;
        this.mobile = mobile;
        this.address = address;
        this.issueDate = issueDate;
    }

	public int getMemberId() {
		return memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public long getMobile() {
		return mobile;
	}


	public String getAddress() {
		return address;
	}

	public int getBookId() {
		return bookId;
	}

	public String getBookName() {
		return bookName;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}
	@Override
	public String toString() {
		return "MemberIssueDTO [memberId=" + memberId + ", memberName=" + memberName + ", bookId=" + bookId
				+ ", bookName=" + bookName + ", mobile=" + mobile + ", address=" + address + ", issueDate=" + issueDate
				+ "]";
	}
}
