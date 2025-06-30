
public class Floor_Calci_Main {

	public static void main(String[] args) {
		Floor floor = new Floor(2.75,4.0);
		Carpet carpet = new Carpet(3.5);
		Floor_Calci calculator = new Floor_Calci(floor,carpet);
		System.out.println("The total cost is :"+calculator.getTotalCost());
		Floor floor_2 = new Floor(5.4,4.5);
		Carpet carpet_2 = new Carpet(1.5);
		Floor_Calci calculator_2 = new Floor_Calci(floor_2,carpet_2);
		System.out.println("The total cost is :"+calculator_2.getTotalCost());

	}

}
