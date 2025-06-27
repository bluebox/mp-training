public class MetClass {
    public static void main(String[] args) {

        int a = 2;
        double d = 4.00d;
        long l = 9l;
        Tot(a, d, l);
    }

        public static void Tot(int a, double b,long l)
        {
            System.out.println("Total Value is " + (a*b+l));
        }
}
