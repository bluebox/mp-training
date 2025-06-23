package learn;

public class BucketCount {
	
	public static void main(String[] args) {
		
		int numberOfBuckets = getBucketCount(3.0, 2.0 ,1.5);
		System.out.println("number of buckets needed are "+numberOfBuckets);
	}
	
	public static int getBucketCount(double width,double height,double areaPerBucket,int extraBuckets) {
		
		if((width < 0) || (height < 0) || (areaPerBucket < 0) || (extraBuckets < 0)) {
			return -1;
		}
		double totalArea = width * height;
		
		return ((int) Math.ceil(totalArea / areaPerBucket) - extraBuckets);
	}
	
	public static int getBucketCount(double area,double areaPerBucket) {
		
		if((areaPerBucket < 0) || (area < 0)) {
			return -1;
		}
		
		return ((int) Math.ceil(area / areaPerBucket));
	}
	public static int getBucketCount(double width,double height,double areaPerBucket) {
		
		if((width < 0) || (height < 0) || (areaPerBucket < 0)) {
			return -1;
		}
		double totalArea = width * height;
		
		return ((int) Math.ceil(totalArea / areaPerBucket));
	}
}
