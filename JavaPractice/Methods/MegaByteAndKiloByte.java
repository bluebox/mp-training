package Methods;

import java.util.*;

public class MegaByteAndKiloByte {
	public static void main(String args[]) {
		Scanner scanner = new Scanner(System.in);
		int kiloBytes = scanner.nextInt();
		printMegaBytesAndKiloBytes(kiloBytes);
		scanner.close();
	}
	public static void printMegaBytesAndKiloBytes(int kiloBytes) {
		if (kiloBytes < 0) {
			System.out.println("Invalid Value");
			return;
		}
		int megaBytes = kiloBytes/1024;
		int remainingKiloBytes = kiloBytes - (1024*megaBytes);
		System.out.println(megaBytes+" MB and "+remainingKiloBytes+" KB");
	}
}
