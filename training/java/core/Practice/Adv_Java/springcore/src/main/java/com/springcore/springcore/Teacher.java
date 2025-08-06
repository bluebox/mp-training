package com.springcore.springcore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Teacher {
	private int teacherId;
	private String teacherName;
	private String subject;
	@Autowired(required = false)
	private Person person;
	
	public Teacher() {
		super();
	}
	public Teacher(int teacherId, String teacherName, String subject) {
		this.teacherId = teacherId;
		this.teacherName = teacherName;
		this.subject = subject;
	}
	public int getTeacherId() {
		return teacherId;
	}
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherName=" + teacherName + ", subject=" + subject + ", person="
				+ person + "]";
	}
	
	
	
}
