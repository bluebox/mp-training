package com.lms.LMS_Springboot.Model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import com.lms.LMS_Springboot.Model.checking_enum.Availability;
import com.lms.LMS_Springboot.Model.checking_enum.Status;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Component
//@Scope("prototype")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Data
public class Book {
	
	
	@NotNull(message="bookid should not be null ")
	private int bookid;
	@NotNull(message="title should not be null")
	@NotBlank(message="title should not be blank")
    private String title;
	@NotNull(message="author should not be null")
	@NotBlank(message="author should not be blank")
    private String author;
	@NotNull(message="category should not be null")
	@NotBlank(message= "category should not be blank")
    private String category;
	@NotNull(message="status should not be null")
    private Status status;
	
    private Availability availability;
   
	

}
