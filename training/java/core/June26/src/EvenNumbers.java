public class EvenNumbers {
    public static void main(String[] args) {
        int number = 5;
        int evenCount = 0 ;
        int OddCount = 0;
        while (number <= 20) {
            if(isEvenNuber(number)){
                System.out.println("even number " +number);

                evenCount ++ ;
                if( evenCount == 5 )
                break;
            }
            else{
                OddCount++;
            }
            number++;
        }
        System.out.println("even numbers count "+ evenCount + " odd numbers count "+ OddCount);
    }
    public static boolean isEvenNuber(int number){
        if(number % 2 == 0)
        return true;
        return false;
    }
}
