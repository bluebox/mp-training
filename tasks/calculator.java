import java.util.Scanner;
public class calculator {
    private double fn;
    private double ln;

    public static void main(String[] args) {
        calculator c = new calculator();
        Scanner sc = new Scanner(System.in);
        System.out.println("enter 1st number:");
        double fn = sc.nextDouble();
        System.out.println("enter 2nd number:");
        double ln = sc.nextDouble();
        c.setfn(fn);
        c.setln(ln);
        System.out.println("addition: " + c.getaddition());
        System.out.println("subtraction: " + c.getsub());
        System.out.println("multiplication: " + c.getmul());
        System.out.println("division: " + c.getdiv());
    }

    public double getFn() {
        return fn;
    }
    public double getLn() {
        return ln;
    }
    public void setfn(double fn) {
        this.fn = fn;
    }
    public void setln(double ln) {
        this.ln = ln;
    }
    public double getaddition() {
        return fn + ln;
    }
    public double getsub() {
        return fn - ln;
    }
    public double getmul() {
        return fn * ln;
    }
    public double getdiv() {
        if (ln == 0) {
            System.out.println("Cannot divide by zero.");
            return 0;
        }
        return fn / ln;
    }
}