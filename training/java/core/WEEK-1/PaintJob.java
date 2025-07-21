public class PaintJob {

    // Method 1: with 4 parameters
    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        }

        double area = width * height;
        int totalBucketsNeeded = (int) Math.ceil(area / areaPerBucket);

        return totalBucketsNeeded - extraBuckets;
    }

    // Method 2: Overloaded with 3 parameters
    public static int getBucketCount(double width, double height, double areaPerBucket) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0) {
            return -1;
        }

        double area = width * height;
        return (int) Math.ceil(area / areaPerBucket);
    }

    // Method 3: Overloaded with 2 parameters
    public static int getBucketCount(double area, double areaPerBucket) {
        if (area <= 0 || areaPerBucket <= 0) {
            return -1;
        }

        return (int) Math.ceil(area / areaPerBucket);
    }

    // Optional: Main method for testing
    public static void main(String[] args) {
        // Method 1 tests
        System.out.println(getBucketCount(-3.4, 2.1, 1.5, 2)); // -1
        System.out.println(getBucketCount(3.4, 2.1, 1.5, 2));  // 3
        System.out.println(getBucketCount(2.75, 3.25, 2.5, 1));// 3

        // Method 2 tests
        System.out.println(getBucketCount(-3.4, 2.1, 1.5));    // -1
        System.out.println(getBucketCount(3.4, 2.1, 1.5));     // 5
        System.out.println(getBucketCount(7.25, 4.3, 2.35));   // 14

        // Method 3 tests
        System.out.println(getBucketCount(3.4, 1.5));          // 3
        System.out.println(getBucketCount(6.26, 2.2));         // 3
        System.out.println(getBucketCount(3.26, 0.75));        // 5
    }
}
