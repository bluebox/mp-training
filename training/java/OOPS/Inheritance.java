
public class Inheritance {

	public static void main(String[] args) {
		Chariot ch = new Chariot(4,2390,"blcak","king's chariot","louis");
		System.out.println(ch.getBrand());
		ch.makeSound();
		System.out.println(ch.getNumber());

	}

}
class Cart
{
	private int wheels;
	private int number;
	private String color;
	private String name;
	public Cart(int wheels, int number, String color, String name) {
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
	
}
class Chariot extends Cart
{
	private String Brand;

	public Chariot(int wheels, int number, String color, String name,String brand) {
		super(wheels,number, color, name);
		this.Brand = brand;
	}
	public Chariot( String color, String name,String brand) {
		this(4,0000, color, name,brand);
		this.Brand = brand;
	}

	@Override
	public void makeSound() {
		System.out.println("sound of chariot");
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public int getWheels() {
		return super.getWheels();
	}

	public int getNumber() {
		return super.getNumber();
	}

	public String getColor() {
		return super.getColor();
	}

	public String getName() {
		return super.getName();
	}

	
}
