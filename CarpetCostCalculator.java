public class CarpetCostCalculator {

    static class Floor {
        private double width;
        private double length;

        public Floor(double width, double length) {
            this.width = width < 0 ? 0 : width;
            this.length = length < 0 ? 0 : length;
        }

        public double getArea() {
            return width * length;
        }
    }

    static class Carpet {
        private double cost;

        public Carpet(double cost) {
            this.cost = cost < 0 ? 0 : cost;
        }

        public double getCost() {
            return cost;
        }
    }

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

    public static void main(String[] args) {
        Carpet carpet = new Carpet(3.5);
        Floor floor = new Floor(2.75, 4.0);
        Calculator calculator = new Calculator(floor, carpet);
        System.out.println("total= " + calculator.getTotalCost());

        carpet = new Carpet(1.5);
        floor = new Floor(5.4, 4.5);
        calculator = new Calculator(floor, carpet);
        System.out.println("total= " + calculator.getTotalCost());
    }
}
