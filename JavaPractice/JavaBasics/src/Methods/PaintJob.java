package Methods;

import java.util.Scanner;

public class PaintJob {
     public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        }

        double area = width * height;
        int bucketsNeeded = (int) Math.ceil(area / areaPerBucket) - extraBuckets;
        return bucketsNeeded > 0 ? bucketsNeeded : 0;
    }

    // Method 2: 3 parameters (no extraBuckets)
    public static int getBucketCount(double width, double height, double areaPerBucket) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0) {
            return -1;
        }

        double area = width * height;
        return (int) Math.ceil(area / areaPerBucket);
    }

    // Method 3: 2 parameters (only area and areaPerBucket)
    public static int getBucketCount(double area, double areaPerBucket) {
        if (area <= 0 || areaPerBucket <= 0) {
            return -1;
        }

        return (int) Math.ceil(area / areaPerBucket);
    }
    public static void main(String[] args) {
        System.out.println(PaintJob.getBucketCount(3.4, 2.1, 1.5, 2));
        System.out.println(PaintJob.getBucketCount(2.75, 3.25, 2.5, 1));
        System.out.println(PaintJob.getBucketCount(3.4, 2.1, 1.5));
        System.out.println(PaintJob.getBucketCount(6.26, 2.2, 2.5));
        System.out.println(PaintJob.getBucketCount(3.4, 1.5));
        System.out.println(PaintJob.getBucketCount(6.26, 2.2));
    }
}
