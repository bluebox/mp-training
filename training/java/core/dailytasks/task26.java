import java.util.Scanner;

public class task26 {

    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        }

        double wallArea = width * height;
        double paintNeededArea = wallArea;
        
        int bucketsNeeded = (int) Math.ceil(paintNeededArea / areaPerBucket);
        
        return bucketsNeeded - extraBuckets;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter wall width: ");
        double width = scanner.nextDouble();

        System.out.print("Enter wall height: ");
        double height = scanner.nextDouble();

        System.out.print("Enter area covered per bucket: ");
        double areaPerBucket = scanner.nextDouble();

        System.out.print("Enter number of extra buckets Bob has: ");
        int extraBuckets = scanner.nextInt();

        int bucketsToBuy = getBucketCount(width, height, areaPerBucket, extraBuckets);

        if (bucketsToBuy == -1) {
            System.out.println("Invalid input parameters.");
        } else if (bucketsToBuy < 0) { // If extra buckets cover more than needed
            System.out.println("Bob doesn't need to buy any buckets. He has enough extra buckets.");
        } else {
            System.out.println("Bob needs to buy " + bucketsToBuy + " buckets.");
        }

        scanner.close();
    }
}