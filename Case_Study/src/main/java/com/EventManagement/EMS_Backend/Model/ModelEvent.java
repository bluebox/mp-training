package com.EventManagement.EMS_Backend.Model;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
@Data
public class ModelEvent {
	@NotNull
	private int Eventid;
	@NotNull
	@NotBlank
	private String Eventname;
	@NotNull
	@NotBlank
	private String Eventdatetime;
	@NotNull
	@NotBlank
	private String Eventvenue;
	@NotNull
	@NotBlank
	private EventCategory Eventcategory;
	@NotNull
	@NotBlank
	private String Approved_by;
	@NotNull
	@NotBlank
	private EventApproveStatus Eventstatus;
	@NotNull
	@NotBlank
	private String EventDescription;
	@NotNull
	@NotBlank
	private String Approved_time;
	
	@NotNull
	@NotBlank
	private String created_by;
	@NotNull
	@NotBlank
	private String created_time;
	@NotNull
	@NotBlank
	private EventStatus Status;
	@NotNull
	@NotBlank
	private String Modified_by;
	@NotNull
	@NotBlank
	private String Modified_at;

}