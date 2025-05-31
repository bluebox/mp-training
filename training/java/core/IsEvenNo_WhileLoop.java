public class IsEvenNo_WhileLoop {

    public static boolean isEven(int n){
        return n%2==0;
    }

   
    public static void main(String[] args) {
         int sum = 0 , cnt =0;
        
        for(int i = 5; i<21; i++){
            if(isEven(i)){
                cnt++;
                sum+=i ;

                if(cnt == 5){
                    System.out.println(sum);
                 break;
                }
            }
        }
    }
}
