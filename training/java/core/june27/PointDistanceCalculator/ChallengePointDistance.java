package com.tulasidhar.june27.PointDistanceCalculator;

public class ChallengePointDistance {
	public static void main(String[] args) {
		Point point = new Point(1,2);
		
		System.out.println(point.distance());
		
		Point point2 = new Point(2,9);
		
		System.out.println(point.distance(point2));
		System.out.println(point.distance(4,3));
	}
}
