package training.java.core.DAY2;

public class PaintJob {
    public static void main(String[] args) {
        System.out.println(getBucketCount(3.26,0.75));
    }
    public static int getBucketCount(double width ,double height,double areaPerBucket,double extraBuckets){
        if(width<=0||height<=0||areaPerBucket<=0){
            return  -1;
        }
        double ans=0;
        double area=width*height;
        ans=Math.ceil(area/areaPerBucket);
        ans=ans-extraBuckets;
        return (int)ans;

    }
    public static int getBucketCount(double width ,double height,double areaPerBucket){
        if(width<=0||height<=0||areaPerBucket<=0){
            return  -1;
        }
        double ans=0;
        double area=width*height;
        ans=Math.ceil(area/areaPerBucket);
        // ans=ans-extraBuckets;
        return (int)ans;

    }
    public static int getBucketCount(double area,double areaPerBucket){
        if(area<=0||areaPerBucket<=0){
            return  -1;
        }
        double ans=0;
        // double area=width*height;
        ans=Math.ceil(area/areaPerBucket);
        // ans=ans-extraBuckets;
        return (int)ans;

    }
}
