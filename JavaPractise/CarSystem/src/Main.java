public class Main {
    public static void main(String[] args) {
        Car petrolCar = new GasCar("Honda", 18.2, 4);
        Car tesla = new ElectricCar("Tesla", 450.0, 75);
        Car hybrid = new HybridCar("Toyota", 22.5, 45, 4);

        petrolCar.startEngine();
        petrolCar.drive();

        System.out.println();

        tesla.startEngine();
        tesla.drive();

        System.out.println();

        hybrid.startEngine();
        hybrid.drive();
    }
}