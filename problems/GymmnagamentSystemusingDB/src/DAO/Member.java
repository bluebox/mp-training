package DAO;

public class Member {

	int id;
	int age;
	String name;
	String Plan;
	public Member(int id, int age, String name, String plan) {
		super();
		this.id = id;
		this.age = age;
		this.name = name;
		Plan = plan;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPlan() {
		return Plan;
	}
	public void setPlan(String plan) {
		Plan = plan;
	}
	

}
