public class problem10 {
   
        public static int reverse(int number) {
        int reversedNumber = 0;
        while (number != 0) {
            int remainder = number % 10;
            reversedNumber = reversedNumber * 10 + remainder;
            number = number / 10;
        }
        return reversedNumber;
    }

    public static void main(String[] args) {
        int num = 12345;
        int reversed = reverse(num);
        System.out.println("Original Number: " + num);
        System.out.println("Reversed Number: " + reversed); 
    }
}
    

