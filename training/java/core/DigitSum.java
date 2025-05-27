public class DigitSum {

    public static void digitSum(int n){
        String s = String.valueOf(n);
        int sum = 0;
        for(int i=0; i<s.length(); i++){
            sum+=Integer.parseInt(s.substring(i,i+1));
        }
        System.out.println(sum);
    }
    public static void main(String[] args) {
        digitSum(123);
    }
}
