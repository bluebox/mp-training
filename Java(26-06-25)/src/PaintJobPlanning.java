
public class PaintJobPlanning {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getBucketCount(3.4,2.1,1.5,2));
		System.out.println(getBucketCount(3.4,2.1,1.5));
		System.out.println(getBucketCount(3.26,0.75));
	
	}
	public static int getBucketCount(double width,double height,double areaPerBucket,double extraBuckets)
	{
		if(width<0||height<0||areaPerBucket<0||extraBuckets<0)
		{
			return -1;
		}
		else
		{
			double x=(width*height)/areaPerBucket;
			return (int)Math.ceil(x-extraBuckets);
		}
	}
	public static int getBucketCount(double width,double height,double areaPerBucket)
	{
		if(width<0||height<0||areaPerBucket<0)
		{
			return -1;
		}
		else
		{
			double x=(width*height)/areaPerBucket;
			return (int)Math.ceil(x);
		}
	}
	public static int getBucketCount(double area,double areaPerBucket)
	{
		if(area<0||areaPerBucket<0)
		{
			return -1;
		}
		else
		{
			double x=area/areaPerBucket;
			return (int)Math.ceil(x);
		}
	}

}
