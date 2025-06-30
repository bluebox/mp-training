public class problem11 {
    public static int sumFirstAndLastDigit(int number) {
        if (number < 0) {
            return -1; 
        }

        int lastDigit = number % 10; 

        
        while (number >= 10) {
            number /= 10;
        }
        int firstDigit = number;

        return firstDigit + lastDigit;
    }

    public static void main(String[] args) {
        System.out.println(sumFirstAndLastDigit(252));  
        System.out.println(sumFirstAndLastDigit(5));    
        System.out.println(sumFirstAndLastDigit(12345)); 
        System.out.println(sumFirstAndLastDigit(-10));  
    }
}

