package com.app.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Feedback {
	
    @NotNull(message="User Id not to be Null!")
    private int userId;
    @NotNull(message="Event Id not to be Null!")
    private int eventId;
    @NotNull(message="Rating not to be Null AND in between(1-10)!")
    private int rating;        
    private String feedback;
    
}
