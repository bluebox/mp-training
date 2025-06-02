public class SumChallenge {
    public static void main(String[] args) {
        int sum=0,cnt=0;
        for(int i=1;i<=1000;i++){
            if(i%15==0){
                System.out.println(i);
                sum+=i;
                cnt++;
                if(cnt==5){
                    break;
                }
            }
        }
        System.out.println(sum);
    }
}
