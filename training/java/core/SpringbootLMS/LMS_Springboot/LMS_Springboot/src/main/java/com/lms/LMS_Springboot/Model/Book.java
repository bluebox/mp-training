package com.lms.LMS_Springboot.Model;

import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Status;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor  // Needed for JSON deserialization
@AllArgsConstructor // Needed for DAO mapping from DB
public class Book {
    private int bookId;
    private String title;
    private String author;
    private String category;
    private Status status;
    private Availability availability;
}
