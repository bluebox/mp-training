package Day2_26_06;

public class PaintJob {

    public static void main(String[] args) {
        System.out.println(getBucketCount(-3.4, 2.1, 1.5));  
        System.out.println(getBucketCount(3.4, 2.1, 1.5));   
        System.out.println(getBucketCount(7.25, 4.3, 2.35)); 
        System.out.println(getBucketCount(7.14, 1.5));       
    }

    public static int getBucketCount(double width, double height, double areaPerBucket) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0) {
            return -1;
        }

        double area = width * height;
        return (int) Math.ceil(area / areaPerBucket);
    }

    public static int getBucketCount(double area, double areaPerBucket) {
        if (area <= 0 || areaPerBucket <= 0) {
            return -1;
        }

        return (int) Math.ceil(area / areaPerBucket);
    }
}

