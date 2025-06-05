package day10.Vehicle;

public class Car {
	
private boolean engine=true;
private int cylinders,wheels=4;
private String name;

Car(int cylinders,String name){
	this.cylinders=cylinders;
	this.name=name;
}

public String startEngine() {
	return name+"'s engine is starting";
}
public String accelerate() {
	return name+" is accelerating";
}
public String brake() {
	return name+" is breaking";
}
public int getCylinders() {
	return this.cylinders;
}
public String getName() {
	return this.name;
}


}
