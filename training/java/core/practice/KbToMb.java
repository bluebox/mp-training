public class KbToMb {
    public static void printMbKb(int kb) {
    	int mb=kb/1024;
    	kb%=1024;
    	System.out.println(mb+" Mb "+kb+" KB");
    } 
	public static void main(String[] args) {
		printMbKb(300100);
	}

}