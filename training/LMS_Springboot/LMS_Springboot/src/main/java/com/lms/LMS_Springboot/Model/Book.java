package com.lms.LMS_Springboot.Model;

import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor  // Needed for JSON deserialization
@AllArgsConstructor // Needed for DAO mapping from DB
public class Book {
    private int bookId;
    
    @NotBlank(message = "Title cannot be empty")
    @Size(min = 4, max = 50, message = "Name must be between 4 and 50 characters")
    private String title;
    private String author;
    private String category;
    private Status status;
    private Availability availability;
}
