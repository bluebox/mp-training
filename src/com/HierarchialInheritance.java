import java.lang.invoke.StringConcatFactory;

public class HierarchialInheritance {
	public static void main(String[] args) {
		Mercedes mercedesCar = new Mercedes("Benz", 1800000, "car X", 180);
		Car tataCar = new Tata("Punch", 200, "Tata motors", "India");
		
		System.out.println(mercedesCar);
		System.out.println(tataCar);
	}
}

class Car {
	public String name;
	public int horsePower;
	
	Car(String name, int horsePower) {
		this.name = name;
		this.horsePower = horsePower; 
	}
}

class Mercedes extends Car {
	String model;
	int price;
	
	Mercedes(String name, int price, String model, int horsePower) {
		super(name, horsePower);
		this.model = model;
		this.price = price;
	}

	@Override
	public String toString() {
		return "Mercedes [model=" + model + ", price=" + price + ", name=" + name + ", horsePower=" + horsePower + "]";
	}
}

class Tata extends Car {
	String manufacturer;
	String countryOriginatedFrom;
	
	Tata(String name, int horsePower, String manufacturer, String countryOriginatedFrom) {
		super(name, horsePower);
		this.manufacturer = manufacturer;
		this.countryOriginatedFrom = countryOriginatedFrom;
	}

	@Override
	public String toString() {
		return "Tata [manufacturer=" + manufacturer + ", countryOriginatedFrom=" + countryOriginatedFrom + ", name="
				+ name + ", horsePower=" + horsePower + "]";
	}
	
}