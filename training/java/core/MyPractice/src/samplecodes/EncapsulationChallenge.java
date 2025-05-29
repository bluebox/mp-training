package samplecodes;

public class EncapsulationChallenge {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printer p=new Printer(75,36,true);
		System.out.println(p.addToner(14));
		System.out.println(p.printPages(38));		
	}

}
class Printer{
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	
	public Printer(int tonerLevel, int pagesPrinted, boolean duplex) {
		this.tonerLevel = tonerLevel;
		this.pagesPrinted = pagesPrinted;
		this.duplex = duplex;
	}
	
	public int getTonerLevel() {
		return tonerLevel;
	}

	public int getPagesPrinted() {
		return pagesPrinted;
	}
	public boolean getDuplex() {
		return duplex;
	}
	public int addToner(int tonerAmount) {
		tonerAmount+=getTonerLevel();
		if(tonerAmount>100) return tonerAmount-1;
		return tonerLevel;
	}
	public int printPages(int pages) {
		pages+=getPagesPrinted();
		if(getDuplex()) {
			System.out.println("Printing on the both sides");
			return (int)Math.ceilDiv(pages,2);
		}
		System.out.println("Printing on the single side");
		return pages;
		
	}
}
