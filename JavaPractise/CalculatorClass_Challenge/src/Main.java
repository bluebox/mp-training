public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.setFirstNumber(10.5);
        calc.setSecondNumber(5.5);

        System.out.println("Addition = "+calc.getAdditionResult());
        System.out.println("Subtraction = "+calc.getSubtractionResult());
        System.out.println("Multiplication = "+calc.getMultiplicationResult());
        System.out.println("Division = "+calc.getDivisionResult());
    }
}
