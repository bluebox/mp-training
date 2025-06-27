package com.tulasidhar.june26;


public class ChallengeExtraPaintCalc {

    public static void main(String[] args) {
        System.out.println(getBucketCount(-3.4, 2.1, 1.5, 2)); 
        System.out.println(getBucketCount(3.4, 2.1, 1.5, 2));  
        System.out.println(getBucketCount(2.75, 3.25, 2.5, 1));
    }

    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if (width <= 0 || height <= 0 || areaPerBucket <= 0 || extraBuckets < 0) {
            return -1;
        }

        double area = width * height;
        double bucketsNeeded = Math.ceil(area / areaPerBucket);

        int totalBucketsToBuy = (int) (bucketsNeeded - extraBuckets);

        return totalBucketsToBuy > 0 ? totalBucketsToBuy : 0;
    }
}
