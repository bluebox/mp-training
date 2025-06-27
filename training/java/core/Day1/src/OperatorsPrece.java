public class OperatorsPrece {
    public static void main(String[] args) {
        double v1=31.00 , v2=80.0 ;
        double rem=((v1+v2)*100)%40;
        boolean b=rem==0;
        if(!b){
            System.out.println("got some remainder");
        }
    }
}
