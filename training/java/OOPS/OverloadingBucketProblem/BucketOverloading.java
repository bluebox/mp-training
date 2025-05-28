package OverloadingBucketProblem;

public class BucketOverloading {

	public static void main(String[] args) {
		double width=2.75,height=3.25,area=2.5,wallArea=73.34;
		int nBuc=1;
		System.out.println(getBucketCount(width,height,area,nBuc));
		System.out.println(getBucketCount(width,height,area));
		System.out.println(getBucketCount(wallArea,area));
	}
	public static int getBucketCount(double width,double height,double area,int nBuc)
	{
		double areaOfWall=width * height;
		int nBuckets= getBucketCount(areaOfWall,area);
		return nBuckets>nBuc?(nBuckets-nBuc):0;
	}
	
	public static int getBucketCount(double width,double height,double area)
	{
		double areaOfWall=width * height;
		int nBuckets= getBucketCount(areaOfWall,area);
		return nBuckets;
	}
	
	public static int getBucketCount(double wallArea,double area)
	{
		int nBuckets= (int) Math.ceil(wallArea/area);
		return nBuckets;
	}
}
