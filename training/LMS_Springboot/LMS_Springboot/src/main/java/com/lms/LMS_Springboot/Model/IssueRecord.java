package com.lms.LMS_Springboot.Model;

import com.lms.LMS_Springboot.Model.checking_enum.Status_issue;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IssueRecord {
    private int issueId;
    private int bookId;
    private int memberId;
    private Status_issue status;
    private LocalDate issueDate;
    private LocalDate returnDate;
}
