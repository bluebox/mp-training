
public class Main1 {
    public static void main(String[] args) {
        SimpleCalculator calculator = new SimpleCalculator();

        calculator.setFirstNumber(5.0);
        calculator.setSecondNumber(43);

        System.out.println("Addition Result = " + calculator.getAdditionResult());
        System.out.println("Subtraction Result = " + calculator.getSubtractionResult());
        System.out.println("Multiplication Result = " + calculator.getMultiplicationResult());
        System.out.println("Division Result = " + calculator.getDivisionResult());
    }
}
