package models;

public abstract class Person {
	private String phone;
    private String name;
    private int age;

    public Person(String phone,String name, int age) {
    	
    	// Updating in db
    	
    	
    	this.phone=phone;
        this.name = name;
        this.age = age;
    }
    
    public String getPhone() {
    	return phone;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    
    public void setName(String name) {
    	this.name=name;
    }
    
    public void setAge(int age) {
    	this.age=age;
    }

    // Abstract method
    public abstract void showDetails();
}
