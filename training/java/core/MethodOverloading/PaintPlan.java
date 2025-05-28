package MethodOverloading;

public class PaintPlan {

	public static int getBucketCount(double width,double height,double areaPerBucket,int extraBuckets) {
		if(width<=0 || height<=0)
			return -1;
		double area=width*height;
		double totalBuckets=(area/areaPerBucket);
		int extraBuckets1=(int) Math.ceil(totalBuckets-extraBuckets);
		return extraBuckets1;

	}

	public static int getBucketCount(double width,double height,double areaPerBucket) {
		if(width<=0 || height<=0)
			return -1;
		double area=width*height;
		double totalBuckets=(area/areaPerBucket);
		return (int) Math.ceil(totalBuckets);

	}

	public static int getBucketCount(double area,double areaPerBucket) {
		if(area<=0)
			return -1;
		double totalBuckets=(area/areaPerBucket);
		return (int) Math.ceil(totalBuckets);
	}
	
	public static void main(String[] args) {
		
		System.out.println("Number of buckets required to paint: "+getBucketCount(7.14,1.5));
		System.out.println();
		System.out.println("Overloaded with width and height feilds.");
		System.out.println("Number of buckets required to paint: "+getBucketCount(3.4,2.1,1.5));
		System.out.println();
		System.out.println("Overloaded with width and height and extra buckets feilds.");
		System.out.println("Number of Extra buckets required to paint: "+getBucketCount(3.4,2.1,1.5,2));

	}
}
