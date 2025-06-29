package com.tulasidhar.june27.WallAreaComputation;

public class ChallengeWallAreaComputation {
	public static void main(String[] args) {
		Wall wall = new Wall(20.3,13.2);
		System.out.println(wall.getArea());
		
		wall = new Wall(-1,-2);
		
		System.out.println(wall.getWidth());
		System.out.println(wall.getHeight());
	}
}