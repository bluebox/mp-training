package samplecodes;

public class MegaByteConverter {
	public static void main(String[] args) {
		printMegaBytesAndKiloBytes(2500);
	}
	public static void printMegaBytesAndKiloBytes(int kiloBytes) {
		int mb=kiloBytes/1024;
		int kb=kiloBytes%1024;
		System.out.println(kiloBytes+"KB = "+mb+"MB and "+kb+"KB");
	}
}
