package June26;

public class Car {
	
	private String make;
	private String model;
	private int doors;
	private boolean convertible;
	private String color;
	
	public Car() {
		this.make = "Tesla";
		this.model = "X";
		this.doors = 4;
		this.convertible = false;
		this.color = "blue";
	}
	
	public Car(String make, String model, int doors, boolean convertible, String color) {
		this.make = make;
		this.model = model;
		this.doors = doors;
		this.convertible = convertible;
		this.color = color;
	}

	public boolean isConvertible() {
		return convertible;
	}

	public void setConvertible(boolean convertible) {
		this.convertible = convertible;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getDoors() {
		return doors;
	}

	public void setDoors(int doors) {
		this.doors = doors;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Car [make=" + make + ", model=" + model + ", doors=" + doors + ", convertible=" + convertible + ", color="
				+ color + "]";
	}
	
}
