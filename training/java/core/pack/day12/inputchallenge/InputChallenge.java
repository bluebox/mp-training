package day12.inputchallenge;


import java.util.Scanner;

public class InputChallenge {
    public static void main(String[] args) {
        int sum = 0;
        int counter = 1;

        Scanner scanner = new Scanner(System.in);
        while (counter <= 10){
            System.out.println("Enter Value #" + counter);
            boolean hasNextInt = scanner.hasNextInt();
            if(hasNextInt){
                sum += scanner.nextInt();
                counter++;
            }else {
                System.out.println("Invalid Value");
            }
            scanner.nextLine(); // End of line enter key also exists which causes endless loop :'(
        }
        System.out.println(sum);
        scanner.close();
    }
}
