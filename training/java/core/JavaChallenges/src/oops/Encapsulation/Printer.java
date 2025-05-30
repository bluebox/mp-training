package oops.Encapsulation;


public class Printer {
	private int tonerLevel;
	private int pagesPrinted;
	private boolean duplex;
	public Printer(int tonerLevel, boolean duplex)
	{
		this.duplex = duplex;
		this.tonerLevel = tonerLevel;
	}
	
	public int addToner(int tonerAmount)
	{
		if(tonerAmount+tonerLevel>100)
		{
			this.tonerLevel=100;
		}
		else if(tonerAmount+tonerLevel<0)
		{
			this.tonerLevel=0;
		}
		else
		{
			this.tonerLevel=tonerLevel+tonerAmount;
		}
		return tonerLevel;
	}
	
	public int printPages(int pages)
	{
		if(duplex)
		{
			System.out.println("The printer is duplex");
		}
		this.pagesPrinted +=pages;
		return pagesPrinted;
	}
	
	
}