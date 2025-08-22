package com.example11;

import org.springframework.stereotype.Component;

@Component
public class Action {
	public Action(){
        System.out.println("Action bean created by Spring");
    }
    private String state = "Surfing";

    
    public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void printHello(){
        System.out.println(
            "Printing Hello from Component Action Bean");
    }

    @Override
    public String toString(){
        return "Action is "+state;
    }
}
