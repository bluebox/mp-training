package BiteConverter;

public class KiloToMegaConverter {

	public static void main(String[] args) {

		int kiloBytes=2500;
		printMegaBytesAndKiloBytes(kiloBytes);

	}
	public static void printMegaBytesAndKiloBytes(int kiloBytes)
	{
		
		int mb=kiloBytes/1024;
		int kb = kiloBytes%1024;
		System.out.println(kiloBytes + " KB = "+ mb + " MB and " + kb +" KB");
	}

}
