public class problem14 {
    
    public static boolean hasSharedDigit(int num1, int num2) {
       


        String sNum1 = String.valueOf(num1);
        String sNum2 = String.valueOf(num2);

        for (int i = 0; i < sNum1.length(); i++) {
            char digit1 = sNum1.charAt(i);

            
            for (int j = 0; j < sNum2.length(); j++) {
                char digit2 = sNum2.charAt(j);

                if (digit1 == digit2) {
                    return true;
                }
            }
        }
      
        return false;
    }

    public static void main(String[] args) {
        
        System.out.println("Shared digit between 12 and 23: " + hasSharedDigit(12, 23)); 
        System.out.println("Shared digit between 456 and 789: " + hasSharedDigit(456, 789)); 
        System.out.println("Shared digit between 101 and 202: " + hasSharedDigit(101, 202)); 
        System.out.println("Shared digit between 7 and 7: " + hasSharedDigit(7, 7)); 
        System.out.println("Shared digit between 123 and 321: " + hasSharedDigit(123, 321)); 
    }
} 

