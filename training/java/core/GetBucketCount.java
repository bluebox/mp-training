
public class GetBucketCount {

    public static void getBucketCOunt(double a , double b){
        if(a < 0 || b < 0){
            System.out.println("invalid");
        }

        else{
            double ans = a / b ;
            System.out.println(Math.ceil(ans));
        }
    }
    public static void main(String[] args) {
        getBucketCOunt(6.26,-2.2);
    }
}
