
public class encapsulation {

	public static void main(String[] args) {
		Vehicles c= new Vehicles(4,555,"black","defender");
		System.out.println(c.getColor());
		System.out.println(c.getName());
		c.setNumber(1234);;
		System.out.println(c.getNumber());
	}

}
class Vehicles
{
	private int wheels;
	private int number;
	private String color;
	private String name;
	public Vehicles(int wheels, int number, String color, String name) {
		this.wheels = wheels;
		this.number = number;
		this.color = color;
		this.name = name;
	}
	
	public void makeSound()
	{
		System.out.println("sound of the cart.....");
	}

	public int getWheels() {
		return wheels;
	}

	public int getNumber() {
		return number;
	}

	public String getColor() {
		return color;
	}

	public String getName() {
		return name;
	}

	public void setWheels(int wheels) {
		this.wheels = wheels;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
