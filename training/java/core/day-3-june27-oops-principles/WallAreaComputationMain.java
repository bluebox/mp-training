package day_3_june27_oops_principles;

class WallAreaComputatationMain {
	public static void main(String[] args) {
		WallAreaComputatation wall1 = new WallAreaComputatation();
		
		// Test 1: Positive dimensions
		wall1.setWidth(5);
		wall1.setHeight(10);
		System.out.println("Test 1: Width = " + wall1.getWidth() + ", Height = " + wall1.getHeight());
		System.out.println("Area = " + wall1.getArea()); // Expected: 50

		// Test 2: Negative width
		wall1.setWidth(-7);
		System.out.println("\nTest 2: Width = " + wall1.getWidth() + ", Height = " + wall1.getHeight());
		System.out.println("Area = " + wall1.getArea()); // Expected: 0

		// Test 3: Negative height
		wall1.setHeight(-3);
		System.out.println("\nTest 3: Width = " + wall1.getWidth() + ", Height = " + wall1.getHeight());
		System.out.println("Area = " + wall1.getArea()); // Expected: 0

		// Test 4: Zero width and height
		wall1.setWidth(0);
		wall1.setHeight(0);
		System.out.println("\nTest 4: Width = " + wall1.getWidth() + ", Height = " + wall1.getHeight());
		System.out.println("Area = " + wall1.getArea()); // Expected: 0

		// Test 5: Reset to valid values
		wall1.setWidth(8);
		wall1.setHeight(6);
		System.out.println("\nTest 5: Width = " + wall1.getWidth() + ", Height = " + wall1.getHeight());
		System.out.println("Area = " + wall1.getArea()); // Expected: 48
	}
}
