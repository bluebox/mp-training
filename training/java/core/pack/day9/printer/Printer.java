package day9.printer;

public class Printer {
private int tonerLevel,pagesPrinted;
private boolean duplex;
Printer(int tonerLevel,boolean duplex){
	this.tonerLevel= (tonerLevel>-1 && tonerLevel<=100)?tonerLevel:-1;
	this.duplex=duplex;
	pagesPrinted=0;
}
public int addToner(int tonerAmount) {
	if((tonerAmount>0 && tonerAmount<=100)&&!((tonerLevel+tonerAmount)>100))
		tonerLevel+=tonerAmount;
	
	else 
		return -1;
	return tonerLevel;
}

public int  printPages(int pages) {
	int pagesToPrint=pages;
	if(this.duplex) {
        pagesToPrint = (pages / 2) + (pages % 2);
        System.out.println("Printing in duplex mode");
    }
    this.pagesPrinted += pagesToPrint;
    return pagesToPrint;
}
public int getPagesPrinted() {
	return this.pagesPrinted;
}

}
