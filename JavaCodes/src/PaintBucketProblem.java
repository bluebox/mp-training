import java.util.*;
public class PaintBucketProblem {
    public static int getBucketCount(double width,double height,double area,int buckets) {
    	if(width<=0 || height<=0||area<=0 || buckets<0)return -1;
         double totalArea=width*height;
         int totalBuckets=(int)Math.round((double)totalArea/area);
         return (int)totalBuckets-buckets;
    }
	public static void main(String[] args) {
		  Scanner sc=new Scanner(System.in);
		  double width=sc.nextDouble();
		  double height=sc.nextDouble();
		  double areaPerBuck=sc.nextDouble();
		  int extraBuck=sc.nextInt();
		  System.out.print(getBucketCount(width,height,areaPerBuck,extraBuck));
	}

}
