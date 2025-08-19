package com.lms.LMS_Springboot.Model;

import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Book {
    private int bookid;

    @NotBlank(message = "title is required")
    private String title;

    @NotBlank(message = "author is required")
    private String author;

    @NotBlank(message = "category is required")
    private String category;

    @NotNull(message = "status is required")
    private Status status;

    @NotNull(message = "availability is required")
    private Availability availability;

    public Book(String title, String author, String category, Status status, Availability availability) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.status = status;
        this.availability = availability;
    }
}
