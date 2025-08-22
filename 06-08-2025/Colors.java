package com.example5;

import org.springframework.stereotype.Component;

@Component
public class Colors {

	private String color;
	
    public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}


	public void printHello(){
        System.out.println(
            "Printing Hello from Component Color Bean");
    }

}