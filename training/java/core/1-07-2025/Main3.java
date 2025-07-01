public class Main3 {
   
    public static void main(String[] args) {
        Printer printer = new Printer(50, true);
        System.out.println(printer.addToner(50)); // Expected: 100
        System.out.println("initial page count = " + printer.getPagesPrinted()); // 0

        int pagesPrinted = printer.printPages(4);
        System.out.println("Pages printed was " + pagesPrinted +
            " new total print count for printer = " + printer.getPagesPrinted());

        pagesPrinted = printer.printPages(2);
        System.out.println("Pages printed was " + pagesPrinted +
            " new total print count for printer = " + printer.getPagesPrinted());
    }
}


