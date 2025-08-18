package com.SpringBoot_LMS.SpringBoot_LMS.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Component
@Data
public class Book {
	    @NotNull(message="id must not be null")
	    private int bookId;
	    @NotBlank(message="title must not be null")
	    @Pattern(regexp="^[a-zA-Z0-9]*$",message="Title must be within these [a-zA-Z0-9_-@?$#!&]")
	    private String title;
	    @NotBlank(message="Author name must Not be Empty")
	    private String author;
	    @NotBlank(message="Category field is Mandatory")
	    private String category;
	    @NotNull(message="Status must be valid")
	    private BookStatus status;  
	    @NotNull(message="Availability must be valid")
	    private BookAvailability availability;
}
