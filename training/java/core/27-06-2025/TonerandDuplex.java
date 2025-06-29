class Printer
{
	private int tonerLevel,pagesPrinted;
	private boolean duplex;
	
	Printer(int tonerLevel,boolean duplex)
	{
		if(tonerLevel<-1 && tonerLevel>100)
			this.tonerLevel=-1;
		else
			this.tonerLevel=tonerLevel;
		
		this.duplex=duplex;
		this.pagesPrinted=0;
	}
	int addToner(int tonerAmount)
	{
		if(tonerAmount>0 && tonerAmount<=100)
		{
			if(tonerLevel+tonerAmount>100)
			{
				return -1;
			}
			tonerLevel+=tonerAmount;
			return tonerLevel;
				
		}
		else
			return -1;
	}
	
	int printPages(int pages)
	{
		int pagesToPrint = pages;
		 if(duplex)
		 {
			 pagesToPrint+=(pages/2)+(pages%2);
		 }
		 pagesPrinted+=pagesToPrint;
		 return pagesToPrint;
	}
	public int getPagesPrinted()
	{
		return pagesPrinted;
	}
}
public class TonerandDuplex {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printer printer=new Printer(60,true);
		System.out.println(printer.addToner(50));
		System.out.println("Intial page count:"+printer.getPagesPrinted());
		int pagesPrinted=printer.printPages(4);
		System.out.println("pages printed was :"+pagesPrinted+"New total print count of printer="+printer.getPagesPrinted());
		pagesPrinted=printer.printPages(2);
		System.out.println("pages printed was"+pagesPrinted+"new total print count for printer="+printer.getPagesPrinted());

	}

}
