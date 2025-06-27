package corejavaday_Two;

public class WordRepresentation {

    public static void main(String[] args){
        System.out.println(printNumInWord(1));
        System.out.println(printNumInWord(3));
        System.out.println(printNumInWord(9));
        System.out.println(printNumInWord(10));
        System.out.println(printNumInWord(-1));

    }
    public  static String printNumInWord(int num){
        return switch(num){
            case 1 -> "one";
            case 2 -> "two";
            case 3 -> "three";
            case 4 -> "four";
            case 5 -> "five";
            case 6 -> "six";
            case 7 -> "seven";
            case 8 -> "eight";
            case 9 -> "nine";
            default -> {
                yield "other";
            }

        };  //after switch closing braces semicolon must.
    }
}
