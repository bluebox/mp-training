
public class KbToMb {
    public static void printMegaBytesSndKiloBytes(int kiloBytes) {
    	double megaByte=kiloBytes/1024;
    	kiloBytes%=1024;
    	System.out.println(megaByte+" Mb "+kiloBytes+" KB");
    } 
	public static void main(String[] args) {
		printMegaBytesSndKiloBytes(2500);
	}

}
