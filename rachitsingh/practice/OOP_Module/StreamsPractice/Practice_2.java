public class Practice_2 {
    public static void main(String[] args) {

        // we are using the reference of interface MathOperation to hold lambda expression
        MathOperation sumOperation = (a, b) -> a + b;
        MathOperation subtractOperation = (a, b) -> a - b;

        int sum = sumOperation.operate(4, 5);
        System.out.println("Sum = " + sum);

        int difference = subtractOperation.operate(5, 4);
        System.out.println("Difference = " + difference);
    }
}

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}