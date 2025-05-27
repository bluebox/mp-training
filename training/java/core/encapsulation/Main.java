
public class Main {

	public static void main(String[] args) {
		Printer printer = new Printer(50,false);
		System.out.println("Toner level: "+printer.addToner(40));
		System.out.println("Initial Page Count is: "+printer.getPagesPrinted());
		int pagesPrinted = printer.printPages(20);
		System.out.println(pagesPrinted+" more pages are printed.\nTotal pages printed are "+printer.getPagesPrinted());
		
	}

}
