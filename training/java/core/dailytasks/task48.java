public class PaintCalculator {

    public static int getBucketCount(double width, double height, double areaPerBucket, double extraBuckets) {
        // Validate input parameters
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1; 
        }

       
        double wallArea = width * height;

       
        double bucketsNeeded = wallArea / areaPerBucket;

       
        int bucketsToBuy = (int) Math.ceil(bucketsNeeded - extraBuckets);

 
        return Math.max(0, bucketsToBuy);
    }
}
