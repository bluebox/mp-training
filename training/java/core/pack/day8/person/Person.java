package day8.person;

public class Person {
 private String firstName,lastName;
 private int age;
 
 public String getFirstName() {
	 return firstName;
 }
 public String getLastName() {
	 return lastName;
 }
 public int getAge() {
	 return age;
 }
 
 public void setFirstName(String s) {
	 this.firstName=s;
 }
 public void setLastName(String s) {
	 this.lastName=s;
 }
 public void setAge(int age) {
	 if(age>100 || age<0) {
		 age=0;
		 return;
	 }
	 this.age=age;
 }
 public boolean isTeen() {
	 return age>12 && age<20;
 }
 public String getFullName() {
	 return firstName+lastName;
 }
 
}
