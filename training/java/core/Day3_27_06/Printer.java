package Day3_27_06;

public class Printer {
	private int tonarLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	public Printer(int tonarLevel, int pagesPrinted, boolean duplex) {
		this.tonarLevel = tonarLevel;
		this.pagesPrinted = pagesPrinted;
		this.duplex = duplex;
	}
	public int addToner(int tonarAmount) {
		if(tonarLevel+tonarAmount>100) {
			return -1;
		}else {
			return tonarLevel+tonarAmount;
		}
	}
	public int printPages(int pages) {
		if(!this.duplex) {
			this.pagesPrinted+=pages;
			return pages;
		}else {
			System.out.println("Its a duplx printer");
			int temp=pages%2==0?pages/2:pages/2+1;
			pagesPrinted+=temp;
			return temp;
		}
	}
	
	public int getPagesPrinted() {
		return pagesPrinted;
	}
	public static void main(String args[]) {
		Printer p1=new Printer(50,12,true);
		System.out.println(p1.printPages(36));
		System.out.println(p1.getPagesPrinted());

	}
}
