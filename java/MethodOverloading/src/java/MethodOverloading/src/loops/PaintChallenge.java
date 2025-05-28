package loops;

public class PaintChallenge {
	public static void main(String [] args) {
		double width = 3.10d;
		double  height = 4.0d;
		double area = 32.0d;
		double areaPerBucket = 3.0d;
		int extraBuckets =2 ;
		System.out.println("No of buckets required to paint the wall With extra buckets");
		int noOfBuckets = getBucketCount(width,height,areaPerBucket,extraBuckets);
		System.out.println(noOfBuckets);
		System.out.println("No of buckets required to paint the wall With out extra buckets");
		noOfBuckets = getBucketCount(width,height,areaPerBucket);
		System.out.println(noOfBuckets);
		System.out.println("No of buckets required to paint the wall With out extra buckets");
		noOfBuckets = getBucketCount(area,areaPerBucket);
		System.out.println(noOfBuckets);
		}
	public static int getBucketCount(double width,double height,double areaPerBucket){
		if(width<0 || height<0 || areaPerBucket <0) {
			return -1;
		}
		double totalArea = width*height;
		return (int)(Math.ceil(totalArea/areaPerBucket));
	}
	public static int getBucketCount(double width,double height,double areaPerBucket, int extraBuckets){
		if(width<0 || height<0 || areaPerBucket <0) {
			return -1;
		}
		double totalArea = width*height;
		return (int)(Math.ceil(totalArea/areaPerBucket)) - extraBuckets;
	}
	public static int getBucketCount(double area,double areaPerBucket){
		if( areaPerBucket <0) {
			return -1;
		}
		
		return (int)(Math.ceil(area/areaPerBucket));
	}
}
