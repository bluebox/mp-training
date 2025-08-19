package com.lms.model;



import java.time.LocalDate;

public class ReturnBook {
    private String mobile;
    private String bookName;
    private String status;
    
    public ReturnBook() {}

    public ReturnBook(String mobile, String bookName, String status) {
        this.mobile = mobile;
        this.bookName = bookName;
        this.status = status;
    }

    public String getMobile() { return mobile; }
    public void setMobile(String mobile) { this.mobile = mobile; }

    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
