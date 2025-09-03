package com.lms.web.model;

public enum Leaves {
	CASUAL_LEAVE(12),
    SICK_LEAVE(12),
	EARNED_LEAVE(45),
    OPTIONAL_LEAVE(2);
	private  int days;

	Leaves(int days) {
	    this.days = days;
	}
	public int getDays() {
        return days;
	}	   
}
