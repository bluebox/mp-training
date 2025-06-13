import java.util.*;
public class OverLoadedMethod {
    public static double toCentimeter(int inches) {
    	return inches*2.54;
    }
    public static double toCentimeter(int feet,int inches) {
    	return toCentimeter((feet*12)+inches);
    }
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		int inches=sc.nextInt();
		System.out.print(toCentimeter(inches));
		int feet=sc.nextInt();
		int inch=sc.nextInt();
		System.out.print(toCentimeter(feet,inch));
	}

}
