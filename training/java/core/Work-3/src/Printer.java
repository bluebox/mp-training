
public class Printer {

	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	public Printer(int tonerLevel, boolean duplex) {
		this.tonerLevel=(tonerLevel>-1&&tonerLevel<=100)?tonerLevel:-1;
		this.duplex=duplex;
		pagesPrinted=0;
	}
	
	public int addToner(int tonerAmount) {
		if(tonerAmount>0&&tonerAmount<=100) {
			if(tonerAmount+tonerLevel<100) {
				return tonerLevel+=tonerAmount;
			}
			else {
				return -1;
			}
		}
		else {
			return -1;
		}
	}
	
	public int printPages(int pages) {
		int pagesToPrint=0;
		if(duplex) {
			pagesToPrint+=pages/2+pages%2;
			pagesPrinted+=pagesToPrint;
			return pagesToPrint;
		}
		else {
			pagesPrinted+=pages;
			return pagesToPrint=pages;
		}
	}

	public int getPagesPrinted() {
		return pagesPrinted;
	}
	
	
	
}
