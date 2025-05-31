public class SumChallengeLoop {
    public static void main(String[] args) {
        int sum = 0;
        for(int i=15; i<=1000; i+=15){
            if(i %15 == 0){
                sum+=i;
            }
        }
        System.out.println(sum);
    }
}
