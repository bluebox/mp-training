package Encapsulation;

public class Printer {
	private int tonerLevel;
	private int pagesPrinted=0;
	private boolean duplex;
	
	public Printer(int level,boolean plex) {
		this.tonerLevel=level;
		this.duplex=plex;
	}
	
	public int addToner(int tonerAmount) {
		if(tonerLevel+tonerAmount >0 && tonerLevel+tonerAmount<100) {
			this.tonerLevel=tonerLevel+tonerAmount;
			return tonerLevel;
		}
		return -1;
	}
	
	public int printPages(int pages) {
		if(duplex) {
			pagesPrinted+=(pages*2);
			System.out.println("It is a duplex printer");
		}
		else {
			pagesPrinted+=pages;
		}
		return pagesPrinted;
	}
	
	public int getTonerLevel(){
	return tonerLevel;
	}
	public int getPagesPrinted(){
	return pagesPrinted;
	}
	public void removeDuplex() {
		this.duplex=false;
	}
	public void setDuplex() {
		this.duplex=true;
	}
	
	public static void main(String[] args) {
		Printer p=new Printer(1,false);
		p.removeDuplex();
		System.out.println("Pages are :"+p.printPages(3));
		System.out.println("Present Toner Level is :"+p.addToner(3));
		p.setDuplex();
		System.out.println("Pages printed are :"+p.getPagesPrinted());
		System.out.println("Present Toner Level is :"+p.addToner(30));
		
	}
}
