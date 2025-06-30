import java.util.Scanner;

public class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        if (tonerLevel > -1 && tonerLevel <= 100) {
            this.tonerLevel = tonerLevel;
        } else {
            this.tonerLevel = -1;
        }
        this.duplex = duplex;
        this.pagesPrinted = 0;
    }

    public int addToner(int tonerAmount) {
        if (tonerAmount > 0 && tonerAmount <= 100) {
            if (this.tonerLevel + tonerAmount > 100) {
                return -1; // Toner level would exceed 100
            } else {
                this.tonerLevel += tonerAmount;
                return this.tonerLevel;
            }
        }
        return -1; // Invalid tonerAmount
    }

    public int printPages(int pages) {
        int pagesToPrint = pages;
        if (this.duplex) {
            pagesToPrint = (pages / 2) + (pages % 2); // Calculate pages for double-sided printing
            System.out.println("Printing in duplex mode.");
        }
        this.pagesPrinted += pagesToPrint;
        return pagesToPrint;
    }

    public int getPagesPrinted() {
        return this.pagesPrinted;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a Printer object with user input
        System.out.print("Enter initial toner level (0-100): ");
        int initialToner = scanner.nextInt();
        System.out.print("Is it a duplex printer (true/false): ");
        boolean isDuplex = scanner.nextBoolean();

        Printer printer = new Printer(initialToner, isDuplex);
        System.out.println("Printer initialized. Toner Level: " + printer.tonerLevel + ", Duplex: " + printer.duplex + ", Pages Printed: " + printer.pagesPrinted);

        // Test addToner method
        System.out.print("Enter toner amount to add (0-100): ");
        int tonerToAdd = scanner.nextInt();
        int newTonerLevel = printer.addToner(tonerToAdd);
        if (newTonerLevel != -1) {
            System.out.println("Toner added. New Toner Level: " + newTonerLevel);
        } else {
            System.out.println("Failed to add toner. Invalid amount or would exceed capacity.");
        }

        // Test printPages method
        System.out.print("Enter number of pages to print: ");
        int pagesToPrint = scanner.nextInt();
        int actualPagesPrinted = printer.printPages(pagesToPrint);
        System.out.println("Actual pages printed for this job: " + actualPagesPrinted);
        System.out.println("Total pages printed: " + printer.getPagesPrinted());

        // Test getPagesPrinted method
        System.out.println("Current total pages printed (using getPagesPrinted): " + printer.getPagesPrinted());

        scanner.close();
    }
}