package encapsulation.challenges;

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
		this.tonerLevel = tonerAmount+tonerLevel<=100?tonerAmount+tonerLevel:100;
		return tonerLevel;
	}
	
	public int printPages(int pages)
	{
		if(duplex)
		{
			System.out.println("The printer is duplex");
		}
		this.pagesPrinted += pagesPrinted+pages;
		return pagesPrinted;
	}
	
	
}
