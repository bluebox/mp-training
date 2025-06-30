import java.util.Scanner;

public class NatoAlphabetChallenge {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a single uppercase letter (A-Z) to get its NATO phonetic equivalent: ");
        String input = scanner.nextLine();

        if (input.length() != 1) {
            System.out.println("Invalid input. Please enter exactly one uppercase letter.");
        } else {
            char natoChar = input.toUpperCase().charAt(0); // Convert to uppercase and get the first character

            switch (natoChar) {
                case 'A':
                    System.out.println(natoChar + " is Able");
                    break;
                case 'B':
                    System.out.println(natoChar + " is Baker");
                    break;
                case 'C':
                    System.out.println(natoChar + " is Charlie");
                    break;
                case 'D':
                    System.out.println(natoChar + " is Dog");
                    break;
                case 'E':
                    System.out.println(natoChar + " is Easy");
                    break;
                case 'F':
                    System.out.println(natoChar + " is Fox");
                    break;
                case 'G':
                    System.out.println(natoChar + " is George");
                    break;
                case 'H':
                    System.out.println(natoChar + " is How");
                    break;
                case 'I':
                    System.out.println(natoChar + " is Item");
                    break;
                case 'J':
                    System.out.println(natoChar + " is Jig");
                    break;
                case 'K':
                    System.out.println(natoChar + " is King");
                    break;
                case 'L':
                    System.out.println(natoChar + " is Love");
                    break;
                case 'M':
                    System.out.println(natoChar + " is Mike");
                    break;
                case 'N':
                    System.out.println(natoChar + " is Nan");
                    break;
                case 'O':
                    System.out.println(natoChar + " is Oboe");
                    break;
                case 'P':
                    System.out.println(natoChar + " is Peter");
                    break;
                case 'Q':
                    System.out.println(natoChar + " is Queen");
                    break;
                case 'R':
                    System.out.println(natoChar + " is Roger");
                    break;
                case 'S':
                    System.out.println(natoChar + " is Sugar");
                    break;
                case 'T':
                    System.out.println(natoChar + " is Tare");
                    break;
                case 'U':
                    System.out.println(natoChar + " is Uncle");
                    break;
                case 'V':
                    System.out.println(natoChar + " is Victor");
                    break;
                case 'W':
                    System.out.println(natoChar + " is William");
                    break;
                case 'X':
                    System.out.println(natoChar + " is X-ray");
                    break;
                case 'Y':
                    System.out.println(natoChar + " is Yoke");
                    break;
                case 'Z':
                    System.out.println(natoChar + " is Zebra");
                    break;
                default:
                    System.out.println(natoChar + " was not found in the NATO phonetic alphabet.");
                    break;
            }
        }
}
}