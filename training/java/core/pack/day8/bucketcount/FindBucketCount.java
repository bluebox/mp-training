package day8.bucketcount;

public class FindBucketCount {
	public static int bucketNeed(double width,double height,double areaPerBucket,int extraBuckets) {
		double requiredArea=width*height;
		double availArea=extraBuckets*areaPerBucket;
		double remaining=requiredArea-availArea;
		if(remaining<=0)
			return 0;
		
		int res=(int) Math.ceil(remaining/areaPerBucket);
		
		return res;
	}
}
