public class Main10 {
    public static void main(String[] args) {
        DPrinter printer = new DPrinter(50, true);
        System.out.println(printer.addToner(56));  
        System.out.println(printer.addToner(50));  

        System.out.println("initial page count = " + printer.getPagesPrinted());

        int pagesPrinted = printer.printPages(4);
        System.out.println("Pages printed was " + pagesPrinted + " new total print count for printer = " + printer.getPagesPrinted());

        pagesPrinted = printer.printPages(2);
        System.out.println("Pages printed was " + pagesPrinted + " new total print count for printer = " + printer.getPagesPrinted());
    }
}
