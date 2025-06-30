class Printer {
    private int tonerLevel;
    private int pagesPrinted;
    private boolean duplex;

    public Printer(int tonerLevel, boolean duplex) {
        if (tonerLevel >= 0 && tonerLevel <= 100) {
            this.tonerLevel = tonerLevel;
        } else {
            this.tonerLevel = -1;
        }
        this.duplex = duplex;
        this.pagesPrinted = 0;
    }

    public int addToner(int tonerAmount) {
        if (tonerAmount > 0 && tonerAmount <= 100) {
            if (tonerLevel + tonerAmount > 100) {
                return -1;
            }
            tonerLevel += tonerAmount;
            return tonerLevel;
        } else {
            return -1;
        }
    }

    public int printPages(int pages) {
        int sheetsToPrint = pages;

        if (duplex) {
            sheetsToPrint = (pages / 2) + (pages % 2);
            System.out.println("Printing in duplex mode");
        }

        pagesPrinted += sheetsToPrint;
        return sheetsToPrint;
    }

    public int getPagesPrinted() {
        return pagesPrinted;
    }
}

public class EncapsulationChallenge {
    public static void main(String[] args) {
        Printer printer = new Printer(50, true);

        System.out.println("Initial toner level: " + printer.addToner(20)); // should return 70

        int pages = printer.printPages(5); // should print 3 sheets
        System.out.println("Pages printed (sheets used): " + pages);
        System.out.println("Total pages printed: " + printer.getPagesPrinted());

        pages = printer.printPages(4); // should print 2 sheets
        System.out.println("Pages printed (sheets used): " + pages);
        System.out.println("Total pages printed: " + printer.getPagesPrinted());

        System.out.println("Try overfilling toner: " + printer.addToner(40)); // should return -1
    }
}
