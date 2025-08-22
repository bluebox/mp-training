
public class Printer {
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	Printer(int tonerLevel,boolean duplex)
	{
		this.tonerLevel=tonerLevel;
		if(this.tonerLevel<0&&this.tonerLevel>100)
		{
			this.tonerLevel=-1;
		}
		pagesPrinted=0;
		this.duplex=duplex;
	}
	public int addToner(int tonerAmount)
	{
		if(tonerAmount>0&&tonerAmount<=100)
		{
		tonerLevel+=tonerAmount;
		if(tonerLevel<0||tonerLevel>100)
		{
			return -1;
		}
	}
		return tonerLevel;
	}
	public int printPages(int pages)
	{
		int sheets=pages;
		if(duplex)
		{
			System.out.println("Printing in Duplex mode");
			sheets= (pages/2)+(pages%2);
		}
			pagesPrinted+=sheets;
			return sheets;
		}
	public int getPagesPrinted()
	{
		return pagesPrinted;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Printer p1=new Printer(50,true);
		System.out.println(p1.addToner(50));
		System.out.println("intial pageCount = "+p1.getPagesPrinted());
		int pagesPrinted=p1.printPages(4);
		System.out.println("Pages Printed was "+pagesPrinted+" new total print count for printer= "+p1.getPagesPrinted());
		

	}

}
