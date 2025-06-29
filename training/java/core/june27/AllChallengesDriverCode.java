package com.tulasidhar.june27;

import com.tulasidhar.june27.CarpetCostCalc.Carpet;
import com.tulasidhar.june27.CarpetCostCalc.CarpetCostCalculator;
import com.tulasidhar.june27.CarpetCostCalc.Floor;
import com.tulasidhar.june27.ComplexNumber.ComplexNumber;
import com.tulasidhar.june27.CuboidPoolArea.Cuboid;
import com.tulasidhar.june27.CuboidPoolArea.Rectangle;
import com.tulasidhar.june27.CylinderWithCircle.Cylinder;
import com.tulasidhar.june27.SmartKichenComposition.SmartKitchen;

public class AllChallengesDriverCode {
	public static void main(String[] args) {
		
		//driver code for CarpetCostCalculator 
		Floor floor = new Floor(2.75,4.0);
		Carpet carpet = new Carpet(3.5);
		
		CarpetCostCalculator calc = new CarpetCostCalculator(floor, carpet);
		System.out.println("Total carpet cost = "+calc.getTotalCost());
		
		
		//Driver code for Complex number operations
		ComplexNumber cm = new ComplexNumber(1,2);
		cm.add(1,2);
		System.out.println(cm.getReal());
		System.out.println(cm.getImaginary());
		
		ComplexNumber cm1 = new ComplexNumber(-1.4,3);
		cm.subtract(cm1);
		
		System.out.println(cm.getReal());
		System.out.println(cm.getImaginary());
		

		//Driver code for Cylinder Class with Circle inheritance
		Cylinder cy = new Cylinder(5.55,7.25);
		System.out.println(cy.getArea());
		System.out.println(cy.getVolume());
		System.out.println(cy.getHeight());
		System.out.println(cy.getRadius());
		
		
		//Driver code for Cuboid Pool Area Calc
		Rectangle rectangle = new Rectangle(5, 10);
        System.out.println("Rectangle area = " + rectangle.getArea());

        Cuboid cuboid = new Cuboid(5, 10, 5);
        System.out.println("Cuboid area = " + cuboid.getArea());
        System.out.println("Cuboid volume = " + cuboid.getVolume());
        
        //Driver code for smart kitchen Composition code
        
        SmartKitchen kitchen = new SmartKitchen();

        kitchen.setKitchenState(true, true, false);
        kitchen.doKitchenWork();

        System.out.println("-----------");

        kitchen.addWater();
        kitchen.doKitchenWork();

        System.out.println("-----------");

        kitchen.loadDishWasher();
        kitchen.pourMilk();
        kitchen.doKitchenWork();
		

	}
}
