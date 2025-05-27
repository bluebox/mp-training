package Encapulation;

class Printer {
	int tonerLevel = 0;// percentage left
	int pagesPrinted;// total pages printed
	boolean duplex;// print both sides or not

	public Printer(boolean duplex) {
		this.duplex = duplex;

	}

	public int addToner(int tonerAmount) {
		System.out.println("Current Toner Level: " + tonerLevel);

		tonerLevel += tonerAmount;
		System.out.println("Updated Toner Level: " + tonerLevel);

		System.out.println("Sufficient to print " + tonerLevel + " pages");
		return tonerLevel;

	}

	public int printPages(int pages) {
		if (tonerLevel < 0) {
			System.out.println("Toner Not available ,Cannaot print.");
		} else if (tonerLevel >= pages) {
			// if one percent toner required to print one page

			System.out.println("Toner Available,You can get your copies in few seconds");
			tonerLevel -= pages;
		} else {
			System.out.println("Sufficient Toner Not Available,try Again after toner filled");

		}
		return 0;

	}

}

public class Challenge_1 {
	public static void main(String[] args) {
		// toner left

		Printer print = new Printer(true);
		print.addToner(80);
		print.printPages(10);
		
		print.addToner(100);
		print.printPages(100);
	}

}
