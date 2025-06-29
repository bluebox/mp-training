import java.util.*;
public class Challenge14 {
	public static int getBucketCount(double width, double height ,double areaPerBucket , int extraBuckets)
	{
		int remainingBuckets=0;
		if(width <= 0 || height <= 0 || areaPerBucket <=0 || extraBuckets <0)
			return -1;
		else
		{
			double areaOfWall=width*height;
			double area=(extraBuckets * areaPerBucket);
			double remaining= areaOfWall-area;
			double ans=remaining/areaPerBucket;
			if(remaining<=0)
			{
				System.out.println("no buckets needed");
				return -1;
			}
			remainingBuckets=(int) Math.floor(ans)+1;
			
		}
		return remainingBuckets;
	}
	
	public static int getNoOfBuckets(double area,double areaPerBucket)
	{
		if(area<0 || areaPerBucket<0)
			return -1;
		else
		{
			return (int) Math.floor((area/areaPerBucket)+1);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("No.Of Remainig Buckets needed is "+getBucketCount(-3.4,2.1,1.5,2));
		System.out.println("No.Of Remainig Buckets needed is "+getBucketCount(2.75,3.25,2.5,1));
		System.out.println("No.Of Remainig Buckets needed is "+getNoOfBuckets(3.4,1.5));
		System.out.println("No.Of Remainig Buckets needed is "+getNoOfBuckets(3.26,0.75));
		

	}

}
