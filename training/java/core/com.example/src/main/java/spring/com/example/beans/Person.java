package spring.com.example.beans;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Component

public class Person {
	private String name="ramu";
	private Vehicle vehicle;
	
	@Autowired
	Person(Vehicle vehicle){
		System.out.println("this is lazy");
		this.vehicle=vehicle;
	}
	
public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
//	public void setVehicle(Vehicle vehicle) {
//		this.vehicle = vehicle;
//	}
	@Override 
	public String toString() {
		return "person is "+this.name+" "+this.vehicle;
	}

}

