package com.example10;

public class Animals {

	public Animals(){
	        System.out.println("Animal bean created by Spring");
	    }

	private String name;
	private Regions region;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Regions getRegion() {
		return region;
	}

	public void setRegion(Regions region) {
		this.region = region;
	}

}
