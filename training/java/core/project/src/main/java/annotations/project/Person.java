package annotations.project;

public class Person {
	public Person() {
		System.out.println("person bean created by spring");
		
	}
	
	private String name;
	private pvehicle vehicle;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public pvehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(pvehicle vehicle) {
		this.vehicle = vehicle;
	}

}
