package corejava.june27_Encapsulation;

public class Printer {
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	public Printer(int tonerLevel, boolean duplex) {
		if(tonerLevel>-1 && tonerLevel<=100) {
			this.tonerLevel = tonerLevel;
		}
		else {
			this.tonerLevel=-1;
		}
		this.pagesPrinted=0;
		this.duplex = duplex;
	}
	
	public int addToner(int tonerAmount) {
		if(tonerAmount>0 && tonerAmount<=100) {
			if((this.tonerLevel+tonerAmount) > 100) {
				return -1;
			}
			return tonerLevel+=tonerAmount;
		}
		return -1;
	}
	
	public int printPages(int pages) {
		int pagesToPrint=pages;
		if(this.duplex) {
			System.out.println("Printing in duplex mode..");
			if(pagesToPrint%2==0)
				this.pagesPrinted+=pages/2;
			else
				this.pagesPrinted+=(pages/2)+1;
			return pagesPrinted;
		}
		return pagesPrinted+pagesToPrint;
	}
	
	public int getPagesPrinted() {
		return pagesPrinted;
	}

}
