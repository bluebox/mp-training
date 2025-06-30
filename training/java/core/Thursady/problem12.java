public class problem12 {
    
           public static int calculateSumOfEvenDigits(int number) {
        int evenSum = 0;
        
        number = Math.abs(number); 

        while (number > 0) {
            int digit = number % 10; 
            if (digit % 2 == 0) { 
                evenSum += digit; 
            }
            number /= 10; 
        }
        return evenSum;
    }

    public static void main(String[] args) {
        int num1 = 123456;
        int sum1 = calculateSumOfEvenDigits(num1);
        System.out.println("Sum of even digits in " + num1 + ": " + sum1); 

        int num2 = 78901;
        int sum2 = calculateSumOfEvenDigits(num2);
        System.out.println("Sum of even digits in " + num2 + ": " + sum2); 

        int num3 = 5;
        int sum3 = calculateSumOfEvenDigits(num3);
        System.out.println("Sum of even digits in " + num3 + ": " + sum3); 
    }
}
    

