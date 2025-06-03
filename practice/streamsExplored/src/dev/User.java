package dev;

public class User {
	private String name;
	private int age;
	private int userId;
	private boolean isActiveUser;
	
	
	public User(String name, int age, int userId, boolean isActiveUser) {
		this.name = name;
		this.age = age;
		this.userId = userId;
		this.isActiveUser = isActiveUser;
	}
	@Override
	public String toString() {
		return "Users [name=" + name + ", age=" + age + ", userId=" + userId + ", isActiveUser=" + isActiveUser + "]";
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public boolean isActiveUser() {
		return isActiveUser;
	}
	public void setActiveUser(boolean isActiveUser) {
		this.isActiveUser = isActiveUser;
	}
	
}
