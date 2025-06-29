package june27_constructors;

import java.util.Scanner;

public class CarpetCostCalculator {

    // Floor class
    static class Floor {
        private double width;
        private double length;

        public Floor(double width, double length) {
            this.width = (width < 0) ? 0 : width;
            this.length = (length < 0) ? 0 : length;
        }

        public double getArea() {
            return width * length;
        }
    }

    // Carpet class
    static class Carpet {
        private double cost;

        public Carpet(double cost) {
            this.cost = (cost < 0) ? 0 : cost;
        }

        public double getCost() {
            return cost;
        }
    }

    // Calculator class
    static class Calculator {
        private Floor floor;
        private Carpet carpet;

        public Calculator(Floor floor, Carpet carpet) {
            this.floor = floor;
            this.carpet = carpet;
        }

        public double getTotalCost() {
            return floor.getArea() * carpet.getCost();
        }
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter width of the floor:");
        double width = sc.nextDouble();

        System.out.println("Enter length of the floor:");
        double length = sc.nextDouble();

        System.out.println("Enter cost per square meter of carpet:");
        double cost = sc.nextDouble();

        Floor floor = new Floor(width, length);
        Carpet carpet = new Carpet(cost);
        Calculator calculator = new Calculator(floor, carpet);

        System.out.println("Total cost to carpet the floor: " + calculator.getTotalCost());
        
        sc.close();
    }
}
