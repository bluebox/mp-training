package com.example;

import java.util.*;

public class Gym{
	HashMap<String,Member> s=new HashMap<>();
	public Gym(HashMap<String, Member> x) {
		this.s=s;
	}
	public HashMap<String, Member> getGym() {
		return s;
	}
	public void setGym(String id,Member x) {
		s.put(id, x);
	}
	public void display() {
		for(String i:s.keySet()) {
			Member m=s.get(i);
			System.out.println("Gym ID : "+m.getGymId()+"\nName : "+m.getName()+"\nAge : "+m.getAge()+"\nHealth Condition : "+m.getHealthCondition()+"\nExperience : "+m.getExperience()+"\nFood Habit : "+m.getFoodHabits()+"\nGoals : "+m.getGoals()+"\nPlan : "+m.getPlan()+"\nDuration : "+m.getDuration()+" months\nTimings"+m.getTimings()+"\nFee : "+m.getFee());
		}
	}
}
