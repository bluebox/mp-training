package corejava.june26_methods;

import java.util.Scanner;

public class PaintWithBucketCalc {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the width of the wall");
		double width=sc.nextDouble();
		System.out.println("Enter the height of the wall");
		double height=sc.nextDouble();
		System.out.println("Enter the area covered by a bucket");
		double areaPerBucket=sc.nextDouble();
		System.out.println("Enter the no of buckets you already have");
		int extraBuckets=sc.nextInt();
		if(getBucketCount(width,height,areaPerBucket,extraBuckets)==-1) {
			System.out.println("You entered an invalid input. We are unable to calculate");
		}
		else {
			System.out.println("You need more "+getBucketCount(width,height,areaPerBucket,extraBuckets)+" to complete the painting");
		}
		sc.close();
	}
	public static int getBucketCount(double width,double height,double areaPerBucket,int extraBuckets) {
		if(width > 0 && height > 0 && areaPerBucket > 0 && extraBuckets >= 0) {
			double area=width*height;
			int bucketsNeeded=(int)Math.ceil(area/areaPerBucket);
			int remainingBuckets=bucketsNeeded-extraBuckets;
			return remainingBuckets;
		}
		return -1;
	}
}
