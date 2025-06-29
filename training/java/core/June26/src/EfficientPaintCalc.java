
public class EfficientPaintCalc {
	public static void main(String[] args) {
		
		System.out.println(getBucketCount(-3.4, 2.1, 1.5, 2)); 
        System.out.println(getBucketCount(3.4, 2.1, 1.5, 2));  
        System.out.println(getBucketCount(2.75, 3.25, 2.5, 1));
        
        System.out.println(getBucketCount(-3.4, 2.1, 1.5)); 
        System.out.println(getBucketCount(3.4, 2.1, 1.5));  
        System.out.println(getBucketCount(7.75, 4.3, 2.35));
        
        System.out.println(getBucketCount(3.4, 1.5)); 
        System.out.println(getBucketCount(6.26, 2.2));  
        System.out.println(getBucketCount(3.26, 0.75));
		
	}
	
	public static int getBucketCount(double width, double height, double areaPerBucket,int extraBuckets) {
		
		if( width <=0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
			return -1;
		}
		
		double bucketsNeeded =Math.ceil( width * height / areaPerBucket);
		
		int BucketsToBuy =  ((int)bucketsNeeded - extraBuckets);
		
		if(BucketsToBuy < 0) {
			BucketsToBuy = 0;
		}
		return BucketsToBuy;
	}
	
	public static int getBucketCount(double width, double height, double areaPerBucket) {
		return getBucketCount(width,height,areaPerBucket,0);
	}
	
	public static int getBucketCount(double area, double areaPerBucket) {
		if( areaPerBucket <= 0 || area < 0) {
			return -1;
		}
		
		return (int) Math.ceil(area / areaPerBucket);
	}
}
