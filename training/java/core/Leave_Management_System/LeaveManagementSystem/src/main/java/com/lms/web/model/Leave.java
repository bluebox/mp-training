package com.lms.web.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor 
public class Leave {

    private int id; 
    @Positive(message = "Employee ID must be a positive number.")
    private int employeeId;
    @NotNull(message = "CasualLeaves cannot be null.")
    @Min(value = 0, message = "CasualLeaves should be greater than or equals to 0")
    @Max(value = 12, message = "CasualLeaves should be less than or equals to 12 ")
    private int CASUAL_LEAVE;
    @NotNull(message = "EarnedLeaves cannot be null.")
    @Min(value = 0, message = "EarnedLeaves should be greater than or equals to 0")
    @Max(value = 15, message = "EarnedLeaves should be less than or equals to 15 ")
    private int EARNED_LEAVE;
    @NotNull(message = "SickLeaves cannot be null.")
    @Min(value = 0, message = "SickLeaves should be greater than or equals to 0")
    @Max(value = 12, message = "SickLeaves should be less than or equals to 12 ")
    private int SICK_LEAVE;
    @NotNull(message = "OptionalLeaves cannot be null.")
    @Min(value = 0, message = "OptionalLeaves should be greater than or equals to 0")
    @Max(value = 2, message = "OptionalLeaves should be less than or equals to 2 ")
    private int OPTIONAL_LEAVE;
    
    public Leave(int employeeId) {
		this.CASUAL_LEAVE = Leaves.CASUAL_LEAVE.getDays();
		this.EARNED_LEAVE = Leaves.EARNED_LEAVE.getDays(); 
		this.SICK_LEAVE = Leaves.SICK_LEAVE.getDays();
		this.OPTIONAL_LEAVE = Leaves.OPTIONAL_LEAVE.getDays();
	}

}
