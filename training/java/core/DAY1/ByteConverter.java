public class ByteConverter {
    public static void main(String[] args) {
        printMegaBytesAndKiloBytes(1024);
    }
    public static void printMegaBytesAndKiloBytes(int kiloBytes){
        if(kiloBytes<0){
            System.out.println("Invalid Input");return;
        }
        int mb=0,kb=0;
        mb=kiloBytes/1024;
        kb=kiloBytes%1024;
        System.out.println(kiloBytes+" KB = " +mb+" MB and "+kb+ " KB");

    }
}
