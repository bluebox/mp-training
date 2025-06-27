public class WhileChallenge {
    public static void main(String[] args) {
        int count=0,i=5;
        while(i<=20){
            if(isEvenNumber(i) ){
                count++;
                if(count<=5) {
                    System.out.println(i + " is even number");
                }
            }
            i++;
        }
        System.out.println("total even numbers : "+count+" total odd numbers : "+(15-count));
    }
    public static boolean isEvenNumber(int num){
        return num%2==0;
    }
}
