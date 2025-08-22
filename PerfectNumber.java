package corejavaday_Two;

public class PerfectNumber {
    public static void main(String[] args){
        System.out.println(isPerfect(-1));
    }
    public static boolean isPerfect(int num){
        if(num<0)
            return false;
        else {
            int sum = 1;
            for (int i = 2; i < num; i++) {
                if (num % i == 0)
                    sum += i;
            }
            if (num==sum) {
                return true;
            }
            else
                return false;
        }
    }
}
