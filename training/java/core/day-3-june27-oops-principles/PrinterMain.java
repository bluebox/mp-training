package day_3_june27_oops_principles;

public class PrinterMain {
    public static void main(String[] args) {
        Printer printer = new Printer(50, true);  // duplex = true

        System.out.println(printer.addToner(50));

        System.out.println("initial page count = " + printer.getPagesPrinted());

        int pagesPrinted = printer.printPages(4); // prints 2 sheets in duplex
        System.out.println("Pages printed was " + pagesPrinted + " new total print count for printer = " + printer.getPagesPrinted());

        pagesPrinted = printer.printPages(2); // prints 1 sheet in duplex
        System.out.println("Pages printed was " + pagesPrinted + " new total print count for printer = " + printer.getPagesPrinted());
    }
}
