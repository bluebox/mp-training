public class IfThen {
    public static void main(String[] args) {
        double d1 = 20.00d;
        double d2 = 80.00d;
        double c = (d1 + d2) * 100.00d ;
        double d = c % 40.00d;
        boolean b = (d == 0.00) ? true : false ;
        System.out.println(b);

        if(b)
        {
            System.out.println("got some remainder");
        }
    }
}
