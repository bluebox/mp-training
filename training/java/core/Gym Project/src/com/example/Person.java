package com.example;

import java.util.List;

public abstract class Person{
	private String GymId;
	private String name;
	private int age; 
	private HealthCondition healthCondition;
	private int experience;
	private List<String> foodHabits;
	private List<String> goals;
	private String timings;
	public  Person(String GymId,String name,int age,HealthCondition healthCondition,int experience,List<String> foodHabit,List<String> goals,String timings){
		this.GymId=GymId;
		this.name=name;
		this.age=age;
		this.healthCondition=healthCondition;
		this.experience=experience;
		this.foodHabits=foodHabit;
		this.goals=goals;
		this.timings=timings;
	}
	public String getGymId() {
		return this.GymId;
	}
	public void setGymid(String GymId) {
		this.GymId=GymId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name=name;
	}
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		this.age=age;
	}
	public HealthCondition getHealthCondition() {
		return this.healthCondition;
	}
	public void setHealthCondition(HealthCondition healthCondition) {
		this.healthCondition=healthCondition;
	}
	public int getExperience() {
		return this.experience;
	}
	public void setExperience(int experience) {
		this.experience=experience;
	}
	public List<String> getFoodHabits() {
		return this.foodHabits;
	}
	public void setFoodHabits(List<String> foodHabits) {
		this.foodHabits=foodHabits;
	}
	public List<String> getGoals() {
		return this.goals;
	}
	public void setGoals(List<String> goals) {
		this.goals=goals;
	}
	public String getTimings() {
		return this.timings;
	}
	public void setTimings(String timings) {
		this.timings=timings;
	}
	public abstract void display();
}
