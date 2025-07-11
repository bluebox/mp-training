package com.app.model;

import lombok.Data;

@Data
public class Feedback {
	
	int user_id;
	int event_id;
	int rating;
	String feedback;
	

}
