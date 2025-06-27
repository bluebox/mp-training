public class Areas {
    public static void main(String[] args) {
        System.out.println(area(5.0));
        System.out.println(area(2.0,3.0));
    }
    public static double area(double n1){
        return (n1>0)?(n1*n1*3.145):-1;
    }

    public static double area(double n1 ,double n2){
        return (n1>0 &&n2>0)?n1*n2:-1;
    }
}
