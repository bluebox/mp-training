package com.kbc.KbcApp.pojos;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kbc.KbcApp.enums.LevelEnum;
import com.kbc.KbcApp.enums.StatusEnum;

import lombok.Data;

@Data

public class Question {

	private Integer questionId;

	@NotBlank(message = "* Question should not be empty")
	@Size(min = 10, max = 250, message = "* Question must be between 20 and 250 characters")
	private String question;

	@NotBlank(message = "* Option 1 must not be empty")
	private String option1;

	@NotBlank(message = "* Option 2 must not be empty")
	private String option2;

	@NotBlank(message = "* Option 3 must not be empty")
	private String option3;

	@NotBlank(message = "* Option 4 must not be empty")
	private String option4;

	@NotNull(message = "* Level is required")
	private LevelEnum level;

	@NotNull(message = "* Status is required")
	private StatusEnum status;

	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	private LocalDateTime createdAt;

	@NotBlank(message = "* Created By must not be empty")
	private String createdBy;

	private LocalDateTime modifiedAt;

	private String modifiedBy;

	public Question() {
	}



	public Question(String question, String option1, String option2, String option3, String option4, LevelEnum level,
			StatusEnum status, String createdBy) {
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.level = level;
		this.status = status;
		this.createdAt = LocalDateTime.now();
		this.createdBy = createdBy;
	}



	public Question(Integer questionId, String question,
			 String option1,
			 String option2,
			  String option3,
			 String option4,
			  LevelEnum level,
			  StatusEnum status, LocalDateTime createdAt,
			  String createdBy) {
		super();
		this.questionId = questionId;
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.level = level;
		this.status = status;
		this.createdAt = createdAt;
		this.createdBy = createdBy;
	}
}
