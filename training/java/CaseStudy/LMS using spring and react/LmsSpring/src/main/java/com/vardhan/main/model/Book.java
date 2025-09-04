package com.vardhan.main.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    
    private Integer id;
    private String bookId;
    
    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title must not exceed 200 characters")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "Title must contain only alphanumeric characters and spaces")
    private String title;
    
    @NotBlank(message = "Author is required")
    @Size(max = 150, message = "Author must not exceed 150 characters")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Author must contain only alphabetic characters and spaces")
    private String author;
    
    @NotBlank(message = "Category is required")
    @Size(max = 50, message = "Category must not exceed 50 characters")
    private String category;
    
    @NotBlank(message = "Status is required")
    @Pattern(regexp = "^(A|I|D)$", message = "Status must be A (Active), I (Inactive), or D (Deleted)")
    private String status;
    
    @NotBlank(message = "Availability is required")
    @Pattern(regexp = "^(A|I)$", message = "Availability must be A (Available) or I (Issued)")
    private String availability;
    
    public boolean isActive() {
        return "A".equals(status);
    }
    
    public boolean isAvailable() {
        return "A".equals(availability);
    }
    
    public boolean isIssued() {
        return "I".equals(availability);
    }
}
