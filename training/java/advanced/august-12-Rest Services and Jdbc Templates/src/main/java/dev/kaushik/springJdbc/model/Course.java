package dev.kaushik.springJdbc.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {

    private Integer id; 

    @NotBlank(message = "Course name is required")
    @Size(max = 255, message = "Course name cannot exceed 255 characters")
    private String name;

    @NotNull(message = "Hours cannot be null")
    @Positive(message = "Hours must be greater than 0")
    private Double hours;

    @Size(max = 1000, message = "Description cannot exceed 1000 characters")
    private String description;
}
