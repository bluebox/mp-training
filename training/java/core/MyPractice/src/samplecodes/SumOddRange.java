package samplecodes;

public class SumOddRange {
    public static boolean isOdd(int number) {
        if (number > 0 && number % 2 != 0) {
            return true;
        }
        return false;
    }

    public static int sumOdd(int start, int end) {
        int sim = 0;
        if (start > 0 && end > 0 && end >= start) {
            for (int i = start; i <= end; i++) {
                if (isOdd(i)) {
                    sim = sim + i;
                }
            }
            return sim;
        }
        return -1;
    }
    
    public static void main(String[] args) {
        

        System.out.println("3 is odd: " + SumOddRange.isOdd(3)); // true
        System.out.println("4 is odd: " + SumOddRange.isOdd(4)); // false
       

        
        System.out.println("Sum of odd numbers from 1 to 5: " + SumOddRange.sumOdd(1, 5)); 
        System.out.println("Sum of odd numbers from 10 to 20: " + SumOddRange.sumOdd(10, 20)); 
        System.out.println("Invalid range (-1 to 10): " + SumOddRange.sumOdd(-1, 10)); 
       
    }
}
