package LOOPS;

public class ForLoop {

    public static int findFactorial(int n ){
        int f = 1;
        for (int i=1; i<=n; i++){
            f =f * i;
        }
        return f;
    }
    public static void main(String[] args) {
        int n = 6;
        System.out.println(findFactorial(n));
        
    }
}
