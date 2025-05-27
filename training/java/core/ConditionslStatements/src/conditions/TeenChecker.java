package conditions;

public class TeenChecker {
	public static boolean hasTeen(int firstNumber, int secondNumber, int thirdNumber) {
        return isTeen(firstNumber) || isTeen(secondNumber) || isTeen(thirdNumber);
    }

    public static boolean isTeen(int number) {
        return number >= 13 && number <= 19;
    }
    public static void main(String[] args) {
        System.out.println(hasTeen(9, 99, 19));    
        System.out.println(hasTeen(23, 15, 42));   
        System.out.println(hasTeen(22, 23, 34));   
        System.out.println(isTeen(13));           
        System.out.println(isTeen(25));             
    }

}
