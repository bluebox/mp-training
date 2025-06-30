import java.util.Scanner;

public class BucketCount {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the number:");
		double wall_area=sc.nextDouble();
		double buc_area=sc.nextDouble();
		System.out.println(getBucketCount(wall_area,buc_area));

	}
	public static int getBucketCount(double wa,double ba) {
		double ans=Math.ceil(wa/ba);
		return (int)ans;
	}

}
