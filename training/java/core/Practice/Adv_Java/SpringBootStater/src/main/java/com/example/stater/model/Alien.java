package com.example.stater.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class Alien {
	private int aid;
	private String aname;
	private String atech;
	@Autowired
	@Qualifier("laptop")
	private Laptop laptop;
	public Alien() {
		super();
		System.out.println("Object Created");
	}
	/*public Alien(int aid, String aname, String atech) {
		this.aid = aid;
		this.aname = aname;
		this.atech = atech;
	}*/
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getAtech() {
		return atech;
	}
	public void setAtech(String atech) {
		this.atech = atech;
	}
	
	public Laptop getLaptop() {
		return laptop;
	}
	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}
	
	public void show() {
		System.out.println("in show");
		laptop.compile();
	}
	
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + ", atech=" + atech + "]";
	}
}
