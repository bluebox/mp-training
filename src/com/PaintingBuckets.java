package com;

class PaintingBuckets {

    public static int getBucketCount(double width, double height, double areaPerBucket, int extraBuckets) {
        if(width < 0 || height < 0 || areaPerBucket < 0 || extraBuckets < 0) return -1;
        double areaOfWall = width * height;
        int totalBucketsNeeded = (int) Math.ceil(areaOfWall / areaPerBucket);
        return totalBucketsNeeded - extraBuckets;
    }

    public static void main(String[] args) {
        // System.out.println(6.8%3.4); -> no error, 0 is the output
        System.out.println(getBucketCount(-3.4, 2.1, 1.5, 2));
        System.out.println(getBucketCount(3.4, 2.1, 1.5, 2));
        System.out.println(getBucketCount(2.75, 3.25, 2.5, 1));
    }

}