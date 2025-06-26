import java.util.stream.Stream;

public class NumberToWord {
    public static void numToWOrd(int n){
        String arr[] = {"zero","one","two","three","four","five","six","seven","eight","nine"};
        String s = String.valueOf(n);

        for(int i=0; i<s.length(); i++)
        {
            System.out.print(arr[Integer.parseInt(s.substring(i,i+1))]+" ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        numToWOrd(123);
    }
}
