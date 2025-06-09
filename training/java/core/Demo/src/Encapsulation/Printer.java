package Encapsulation;

public class Printer {
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	public Printer(int tonerLevel, boolean duplex) {
		if(tonerLevel<-1 || tonerLevel>100) this.tonerLevel=-1;
		else this.tonerLevel=tonerLevel;
		
		this.pagesPrinted=0;
		this.duplex=duplex;
	}
	
	public int getPagesPrinted() {
		System.out.println("Printing in duplex mode");
		return pagesPrinted;
	}
	
	public int printPages(int pages) {
		int pagesToPrint=pages;
		if(duplex) {
			pagesToPrint/=2;
		}
		pagesPrinted+=pagesToPrint;
		return pagesToPrint;
	}
	public int addToner(int tonerAmount) {
		if(tonerAmount>0 && tonerAmount<=100) {
			if(tonerLevel+tonerAmount<=100) {
				tonerLevel+=tonerAmount;
				return tonerLevel;
			} 
			return -1;
		}
		return -1;
		
	}

}
