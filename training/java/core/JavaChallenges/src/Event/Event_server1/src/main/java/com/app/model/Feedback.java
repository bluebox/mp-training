package com.app.model;

import lombok.Data;

@Data
public class Feedback {
    private int user_id;
    private int event_id;
    private int rating;        
    private String feedback;
	
}
