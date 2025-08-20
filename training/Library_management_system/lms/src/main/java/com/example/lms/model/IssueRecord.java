package com.example.lms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecord {
    private Long issueId;     
    private Long bookId;     
    private Long memberId;    
    private String status;    
    private LocalDate issueDate;   
    private LocalDate returnDate; 
}
