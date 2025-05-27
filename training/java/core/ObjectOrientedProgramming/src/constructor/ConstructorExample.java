package constructor;

class Vehicle
{
	private int kiloMeters;
	private boolean accident;
	private String dateOfRegistration;
	
	public Vehicle(int kiloMeters, boolean accident, String dateOfRegistration) {
		this.kiloMeters = kiloMeters;
		this.accident = accident;
		this.dateOfRegistration = dateOfRegistration;
	}

	public int getKiloMeters() {
		return kiloMeters;
	}

	public void setKiloMeters(int kiloMeters) {
		this.kiloMeters = kiloMeters;
	}

	public boolean isAccident() {
		return accident;
	}

	public void setAccident(boolean accident) {
		this.accident = accident;
	}

	public String getDateOfRegistration() {
		return dateOfRegistration;
	}

	public void setDateOfRegistration(String dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}

	@Override
	public String toString() {
		return "Vehicle [kiloMeters=" + kiloMeters + ", accident=" + accident + ", dateOfRegistration="
				+ dateOfRegistration + "]";
	}
	
	
	
	
}

public class ConstructorExample {
	
	public static void main(String args[])
	{
		Vehicle alto = new Vehicle(0, false, "10-01-2000");
		System.out.println(alto);
	}
	
}
