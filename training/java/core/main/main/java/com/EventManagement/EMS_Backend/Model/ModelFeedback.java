package com.EventManagement.EMS_Backend.Model;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ModelFeedback {
	@NotNull
	private String userId;
	@NotNull
	@NotBlank 
	private int eventId;
	@NotNull
	@NotBlank 
	private String description;
	@NotNull
	private int rating;

}
