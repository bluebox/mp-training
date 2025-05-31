public class EvenDigitSum {

    public static void evenDigitSum(int n){
        String s = String.valueOf(n);
        int sum = 0;
        for(int i =0; i<s.length(); i++){
            int x = Integer.parseInt(s.substring(i,i+1));
            if(x%2 == 0 ) sum+=x;
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        evenDigitSum(1234);
    }
}
