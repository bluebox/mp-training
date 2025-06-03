package com.example;

import java.util.*;

public class Trainee {
	private Trainer trainee;
	HashMap<String,Integer> schedule=new HashMap<>();
	List<Member> candidates=new ArrayList<>();
	public Trainee(Trainer trainee,List<Member> candidates) {
		this.trainee=trainee;
		this.schedule.put("05:00 AM-07:30 AM", 0);
		this.schedule.put("07:30 AM-10:00 AM", 0);
		this.schedule.put("05:00 PM-07:30 PM", 0);
		this.schedule.put("07:30 PM-10:00 PM", 0);
		this.candidates=candidates;
	}
	public Trainer getTrainee() {
		return this.trainee;
	}
	public void setTrainee(Trainer trainee) {
		this.trainee=trainee;
	}
	public HashMap<String, Integer> getSchedule() {
		return this.schedule;
	}
	public void setSchedule(HashMap<String,Integer> schedule) {
		this.schedule=schedule;
	}
	public List<Member> getCandidates() {
		return this.candidates;
	}
	public void setCandidates(List<Member> candidates) {
		this.candidates=candidates;
	}
}
