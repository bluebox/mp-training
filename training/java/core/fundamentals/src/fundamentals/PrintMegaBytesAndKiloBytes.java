package fundamentals;

public class PrintMegaBytesAndKiloBytes {
	public static void main(String args[])
	{
		printMegaBytesAndKiloBytes(2500);
	}
	static void printMegaBytesAndKiloBytes(int kb)
	{
		if(kb<0)
		{
			System.out.println("Invalid Value.");
			return;
		}
		int mb=kb/1000;
		int remKb=kb%1000;
		System.out.println(kb+" kb ="+mb+" mb and "+remKb+" kb");
	}
}
