public class ByteConversion {

    public static void megaByteConverter(int kb){
        int mb = (int) kb / 1024;
        int rem = kb%1024;

        System.out.printf("%d mb and %d kb",mb,rem);
    }
    public static void main(String[] args) {
        int kb = 2500;
        megaByteConverter(kb);
    }
}
