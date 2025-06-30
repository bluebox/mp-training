package challenges_30th_june.CarClassChallenge;

public class Main {
    public static void main(String[] args) {
        Car gasCar = new GasPoweredCar("Ford Mustang", 15.5, 6);
        Car electricCar = new ElectricCar("Tesla Model S", 400, 100);
        Car hybridCar = new HybridCar("Toyota Prius", 20, 30, 4);

        Car[] cars = { gasCar, electricCar, hybridCar };

        for (Car car : cars) {
            System.out.println("\nRuntime object type: " + car.getClass().getSimpleName());
            car.startEngine();
            car.drive();
        }
    }
}

