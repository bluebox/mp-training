package com.example.stater.model;

import org.springframework.stereotype.Component;

@Component("laptop")
public class Laptop {
	private int lid;
	private String lbrand;
	
	public Laptop() {
		super();
	}
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getLbrand() {
		return lbrand;
	}
	public void setLbrand(String lbrand) {
		this.lbrand = lbrand;
	}
	public void compile() {
		System.out.println("Compiling");
	}
	
	@Override
	public String toString() {
		return "Laptop [lid=" + lid + ", lbrand=" + lbrand + "]";
	}
}
