package com.medplus.lms.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberIssuedBookDto {
    private int memberId;
    private String name;
    private String email;
    private int bookId;
    private String bookTitle;
    private LocalDateTime issueDate;
}
