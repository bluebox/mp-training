package corejava.june27_Encapsulation;

public class PrinterMain {

	public static void main(String[] args) {
		Printer printer=new Printer(50,true);
		System.out.println(printer.addToner(50));
		System.out.println("Initial page count ="+printer.getPagesPrinted());
		int pagesPrinted=printer.printPages(4);
		System.out.println("Pages printed was "+pagesPrinted+" new total print count for printer = "+printer.getPagesPrinted());
		pagesPrinted=printer.printPages(pagesPrinted);
		System.out.println("Pages printed was "+pagesPrinted+" new total print count for printer = "+printer.getPagesPrinted());
	}

}
