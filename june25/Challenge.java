package june25;

public class Challenge {
    public static void main(String[] args) {
        double var1 = 20.00;
        double var2 = 80.00;
        double var3 = (var1+var2)*100.0;
        //var3+=1;
        System.out.println("Calculated Value "+var3);
        double rem = var3%40.00;
        System.out.println("Remainder "+rem);
        boolean var4 = rem==0.00 ? true:false;
        System.out.println(var4);
        if(!var4) System.out.println("got some remainder");
    }
}
