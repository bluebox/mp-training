public class GCD {

    public static int findGcd(int a , int b){

        if (b == 0) return a;
        return findGcd(b, a%b);
        
    }
    public static void main(String[] args) {
        System.out.println(findGcd(24,32));
    }
}
