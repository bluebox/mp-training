package com.lms.LMS_Springboot.Model;

import com.lms.LMS_Springboot.Model.checking_enum.Status_issue;
import jakarta.validation.constraints.NotNull;
import java.sql.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Data
@NoArgsConstructor
@Component
public class Issue_records {

    private int isssueid;

    @NotNull(message = "Member ID is required")
    private Integer memberid;

    @NotNull(message = "bookid is required")
    private Integer bookid;

    @NotNull(message = "issue date is required")
    private Date issuedate;

    private Date returndate;  

    @NotNull(message = "issuestatus is required")
    private Status_issue status_issue;

    public Issue_records(int bookid, int memberid, Status_issue status_issue, Date issuedate) {
        this.memberid = memberid;
        this.bookid = bookid;
        this.issuedate = issuedate;
        this.status_issue = status_issue;
    }
}
