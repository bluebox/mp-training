
public class Methods {
	public static void main(String[] args) {
		
		// convert Pound to kilo grams
		System.out.println("Converted Pounds to Kilo Grams");
		System.out.println(1+" - "+PtoKg(1));
		System.out.println(3+" - "+PtoKg(3));
		System.out.println(5+" - "+PtoKg(5));
		
		//add two nums
		System.out.println("Sum of 1 & 2 "+"- "+add(1,2));
		System.out.println("Sum of 20 & 30 "+"- "+add(20,30));
		System.out.println("Sum of 40 & 50 "+"- "+add(40,50));



	}
	
	public static double PtoKg(int pound) {
		
		double kg = pound * 0.45359237;
		return kg;
	}
	
	public static int add(int a,int b) {
		return a+b;
	}

}
