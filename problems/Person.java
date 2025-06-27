package problems;

public class Person {
    private  String First_Name;
    private  String Last_Name;
    private  int age;
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		if(age<0 || age>100) {this.age=0;}
		else{this.age = age;}
	}
	
	public boolean isTeen() {
		if(age>12 && age<20)return true;
		
		return false;
	}
	
	public String getFullName() {
		return this.getFirst_Name()+this.getLast_Name();
	}
	
}
