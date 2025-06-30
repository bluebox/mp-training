public class PaintCalculator {

    public static int getBucketCount(double width, double height, double areaPerBucket, double extraBuckets) {
        // Validate input parameters
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1; // Indicate invalid value
        }

        // Calculate the total wall area
        double wallArea = width * height;

        // Calculate the number of buckets needed without considering extra buckets
        double bucketsNeeded = wallArea / areaPerBucket;

        // Calculate the number of buckets to buy
        // Math.ceil() is used to round up to the nearest whole number, as you can't buy a fraction of a bucket.
        int bucketsToBuy = (int) Math.ceil(bucketsNeeded - extraBuckets);

        // Ensure we don't return a negative number of buckets to buy
        return Math.max(0, bucketsToBuy);
    }
}