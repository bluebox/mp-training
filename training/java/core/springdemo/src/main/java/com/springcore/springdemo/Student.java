package com.springcore.springdemo;

public class Student {
 private int studentId;
 private String studentName;
 private String studentAddress;
 public Student(int studentId, String studentName, String studentAddress) {
	super();
	this.studentId = studentId;
	this.studentName = studentName;
	this.studentAddress = studentAddress;
}
 public int getStudentId() {
	return studentId;
 }
 public void setStudentId(int studentId) {
	this.studentId = studentId;
 }
 @Override
public String toString() {
	return "Student [studentId=" + studentId + ", studentName=" + studentName + ", studentAddress=" + studentAddress
			+ "]";
}
 public Student() {
	super();
	// TODO Auto-generated constructor stub
}
 public String getStudentName() {
	return studentName;
 }
 public void setStudentName(String studentName) {
	this.studentName = studentName;
 }
 public String getStudentAddress() {
	return studentAddress;
 }
 public void setStudentAddress(String studentAddress) {
	this.studentAddress = studentAddress;
 }
}
