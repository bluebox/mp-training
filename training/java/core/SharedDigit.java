public class SharedDigit {

    public static void sharedDigit(int a , int b){
        if (a >= 10 && b<=99){
            System.out.println("true"); 
        }
        else{
            if (a%10 == b%10) {
                System.out.println("true");
            }
            int first = (int) a / 10;
            int second_first = (int) b/10; 

            if(first == second_first){
                System.out.println("true");
            }

        }
    }
    public static void main(String[] args) {
        sharedDigit(12,21);
    }
}
