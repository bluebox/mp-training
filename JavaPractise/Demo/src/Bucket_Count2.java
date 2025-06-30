import java.util.Scanner;

public class Bucket_Count2 {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the length breadth bucket_area:");
		double length=sc.nextDouble();
		double breadth=sc.nextDouble();
		double buc_area=sc.nextDouble();
		System.out.println(getBucketCount(length,breadth,buc_area));
		System.out.println("Eneter the wall area and buc area:");
		double area=sc.nextDouble();
		buc_area=sc.nextDouble();
		System.out.println(getBucketCount(area,buc_area));
		

	}
	public static int getBucketCount(double length,double breadth,double ba) {
		if(length<0 || breadth<0 || ba<0) return -1;
		double total_area=length*breadth;
		double ans=Math.ceil(total_area/ba);
		return (int)ans;
	}
	public static int getBucketCount(double area,double ba) {
		if(area<0|| ba<0) return -1;
		double ans=Math.ceil(area/ba);
		return (int)ans;
	}

}
