package day8.bucketcount;

import java.util.Scanner;

public class BucketCount  {
	public static void main(String args[]) throws Exception {
		Scanner sc=new Scanner(System.in);
		int extraBuckets;
		double width,height,areaPerBucket;
		
	try{
		while(true) {
			System.out.println("Enter width of the wall enter -1 to exit");
			width=sc.nextDouble();
			if(width<=0)
				throw new Exception();
			
			System.out.println("Enter height of the wall enter -1 to exit");
			height=sc.nextDouble();
			if(height<=0)
				throw new Exception();
			
			System.out.println("Enter area per bucket enter -1 to exit");
			areaPerBucket=sc.nextDouble();
			if(areaPerBucket<0)
				throw new Exception();
			
			System.out.println("Enter extra buckets enter -1 to exit");
			extraBuckets=sc.nextInt();
			if(extraBuckets==-1)
				throw new Exception();
			
			System.out.println(FindBucketCount.bucketNeed(width,height,areaPerBucket,extraBuckets));
			System.out.println("------------------------------------");
		}
	}
	catch(Exception e){
	System.out.println("Raised Exception try again with valid inputs");
	}
	}
}
