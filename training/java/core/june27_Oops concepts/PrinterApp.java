package june27_constructors;
import java.util.Scanner;

public class PrinterApp {

    static class Printer {
        private int tonerLevel;
        private int pagesPrinted;
        private boolean duplex;

        // Constructor
        public Printer(int tonerLevel, boolean duplex) {
            if (tonerLevel > -1 && tonerLevel <= 100) {
                this.tonerLevel = tonerLevel;
            } else {
                this.tonerLevel = -1;
            }
            this.duplex = duplex;
            this.pagesPrinted = 0;
        }

        // Add toner
        public int addToner(int tonerAmount) {
            if (tonerAmount > 0 && tonerAmount <= 100) {
                if (this.tonerLevel + tonerAmount > 100) {
                    return -1;
                } else {
                    this.tonerLevel += tonerAmount;
                    return this.tonerLevel;
                }
            } else {
                return -1;
            }
        }

        // Print pages
        public int printPages(int pages) {
            int pagesToPrint = pages;
            if (this.duplex) {
                pagesToPrint = (pages / 2) + (pages % 2);
                System.out.println("Printing in duplex mode");
            } else {
                System.out.println("Printing in single-sided mode");
            }
            this.pagesPrinted += pagesToPrint;
            return pagesToPrint;
        }

        // Get total pages printed
        public int getPagesPrinted() {
            return pagesPrinted;
        }

        // Get toner level
        public int getTonerLevel() {
            return tonerLevel;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("------ Printer Setup ------");
        System.out.print("Enter initial toner level (0-100): ");
        int initialToner = sc.nextInt();

        System.out.print("Is the printer duplex? (true/false): ");
        boolean duplex = sc.nextBoolean();

        Printer printer = new Printer(initialToner, duplex);

        while (true) {
            System.out.println("\n------ Printer Menu ------");
            System.out.println("1. Add Toner");
            System.out.println("2. Print Pages");
            System.out.println("3. Check Toner Level");
            System.out.println("4. Check Total Pages Printed");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter toner amount to add: ");
                    int tonerAmount = sc.nextInt();
                    int newToner = printer.addToner(tonerAmount);
                    if (newToner == -1) {
                        System.out.println("Toner addition failed. Exceeds limit or invalid input.");
                    } else {
                        System.out.println("Toner added. Current toner level: " + newToner + "%");
                    }
                    break;
                case 2:
                    System.out.print("Enter number of pages to print: ");
                    int pages = sc.nextInt();
                    int sheets = printer.printPages(pages);
                    System.out.println("Pages printed: " + sheets);
                    System.out.println("Total pages printed so far: " + printer.getPagesPrinted());
                    break;
                case 3:
                    System.out.println("Current toner level: " + printer.getTonerLevel() + "%");
                    break;
                case 4:
                    System.out.println("Total pages printed so far: " + printer.getPagesPrinted());
                    break;
                case 5:
                    System.out.println("Exiting... Thank you.");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
