package june30_collections;
import java.util.Scanner;

public class CarMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n------ Car Menu------- ");
            System.out.println("1. Gas Powered Car");
            System.out.println("2. Electric Car");
            System.out.println("3. Hybrid Car");
            System.out.println("4. Exit");
            System.out.print("Choose Car Type (1-4): ");

            int choice = sc.nextInt();

            if (choice == 4) {
                System.out.println("Thank you! You are Exited");
                break;
            }

            Car car = null;
            String description;

            switch (choice) {
                case 1:
                    System.out.print("Enter Car Name/Description: ");
                    description = sc.nextLine();

                    System.out.print("Enter Avg Km Per Litre: ");
                    double avgKmPerLitre = sc.nextDouble();

                    System.out.print("Enter Number of Cylinders: ");
                    int cylinders = sc.nextInt();

                    car = new GasPoweredCar(description, avgKmPerLitre, cylinders);
                    break;

                case 2:
                    System.out.print("Enter Car Name/Description: ");
                    description = sc.nextLine();

                    System.out.print("Enter Avg Km Per Charge: ");
                    double avgKmPerCharge = sc.nextDouble();

                    System.out.print("Enter Battery Size (in KWh): ");
                    int batterySize = sc.nextInt();

                    car = new ElectricCar(description, avgKmPerCharge, batterySize);
                    break;

                case 3:
                    System.out.print("Enter Car Name/Description: ");
                    description = sc.nextLine();

                    System.out.print("Enter Avg Km Per Litre: ");
                    double hybridAvgKm = sc.nextDouble();

                    System.out.print("Enter Battery Size (in KWh): ");
                    int hybridBatterySize = sc.nextInt();

                    System.out.print("Enter Number of Cylinders: ");
                    int hybridCylinders = sc.nextInt();

                    car = new HybridCar(description, hybridAvgKm, hybridBatterySize, hybridCylinders);
                    break;

                default:
                    System.out.println("Invalid choice! Please enter between 1 and 4.");
                    continue;
            }

          
            System.out.println("\n--- Car Operations ---");
            car.startEngine();
            car.drive();

            System.out.println("\nThis car is of type: " + car.getClass().getSimpleName());
        }
    }
}
