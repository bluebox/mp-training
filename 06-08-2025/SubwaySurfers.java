package com.example11;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubwaySurfers {

	private String playername="Tricky";
	private Action action;
	
	@Autowired
	public SubwaySurfers(Action action) {
		System.out.println("Subway Surfers bean is created ");
		this.action = action;
	}

	public String getPlayername() {
		return playername;
	}

	public void setPlayername(String playername) {
		this.playername = playername;
	}

	public Action getAction() {
		return action;
	}
}
