package TonerAndDuplexPrinting;

public class Printer {
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	public Printer(int tonerLevel, boolean duplex) {
		if(tonerLevel < -1 || tonerLevel > 100) {
			tonerLevel = -1;
		}
		this.tonerLevel = tonerLevel;
		this.duplex = duplex;
		pagesPrinted =0;
	}
	
	public int addToner(int tonerAmount) {
		if(tonerAmount > 0 && tonerAmount <= 100) {
			if(tonerAmount + tonerLevel > 100) {
				return -1;
			}
			tonerLevel = tonerLevel + tonerAmount;
			return tonerLevel;
		}
		return -1;
	}
	
	public int printPages(int pages) {
		int pagesToPrint = pages;
		
		if(duplex) {
			System.out.println("Printing in duplex mode");
			pagesToPrint= (pagesToPrint % 2 ==0 )? pagesToPrint / 2 : pagesToPrint + 1;
			pagesPrinted += pagesToPrint;
			return pagesToPrint;
		}
		else {
			System.out.println("Printing not in duplex mode");
			pagesPrinted+=pagesToPrint;
			return pagesToPrint;
		}
	}
	
	public int getPagesPrinted() {
		return pagesPrinted;
	}
}
