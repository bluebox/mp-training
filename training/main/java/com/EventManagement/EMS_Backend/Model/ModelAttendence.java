package com.EventManagement.EMS_Backend.Model;


import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Component
public class ModelAttendence {
	@NotNull
	@NotBlank
	private int  Att_Id;
	@NotNull
	@NotBlank

	@NotNull
	private int UserId;
	@NotNull
	private int EventId;
	@NotNull
	@NotBlank
	private Attendenceenum Presence;



}
