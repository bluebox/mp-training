
public class InchToCm {
	static double inchToCm(int x) {
		return x*2.54;
	}
	static double inchToCm(int x,int y) {
		return ((x*12)+y)*2.54;
	}
	public static void main(String arg[]) {
		System.out.println("43 inches= "+inchToCm(43)+" cm");
		System.out.println("2 feet 35 inches= "+inchToCm(2,35)+" cm");
	}
}
