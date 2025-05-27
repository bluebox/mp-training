public class EvenNumber {
    public static void main(String[] args) {
        int i=5,n=20,cnt=0;
        while(i<=n){
            if(isEvenNumber(i)){
                cnt++;
                System.out.println(i);
                if(cnt==5){
                    break;
                }
            }
            i++;
        }
        System.out.println("odd numbers found = "+(16-cnt));
        System.out.println("even numbers found = "+cnt);
    }
    public static boolean isEvenNumber(int n){
        if(n%2==0){
            return true;
        }
        return false;
    }
}
