package corejavaday_Two;
import java.util.Scanner;
import java.lang.Math;
public class PaintJob {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        double width=input.nextDouble();
        double height=input.nextDouble();
        double areaPerBucket=input.nextDouble();
        int extraBucket=input.nextInt();
        double area=input.nextDouble();
        System.out.println(extraBucketCount(width,height,areaPerBucket,extraBucket));
        System.out.println(extraBucketCount(area,areaPerBucket));
    }
    public static int extraBucketCount(double width,double height,double areaPerBucket,int extraBucket){
        if(width<=0 || height<=0 || areaPerBucket<=0 || extraBucket<0){
            return -1;
        }
        double area=width*height;
        double paintNeed=area/areaPerBucket;
        int required=(int)(Math.round(paintNeed)-extraBucket);
        return required;
    }
    public static int extraBucketCount(double area,double areaPerBucket){
        if(area<=0 || areaPerBucket<=0) {
            return -1;
        }
        int res=(int)(Math.round(area/areaPerBucket));
        return res;
    }
}
