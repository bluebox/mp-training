package com.app.model;

import lombok.Data;

@Data
public class Feedback {
    private int userId;
    private int eventId;
    private int rating;        
    private String feedback;
	
}
