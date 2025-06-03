public class MultipleInheritance {
    public static void main(String[] args) {
        Calculator calci = new Calculator();
        calci.add(5, 4);
        calci.mul(5, 4);
    }
}

class Calculator implements A, B{
    public void add(int a, int b){
        System.out.println("Addition is: " + (a + b));
    }
    public void mul(int a, int b) {
        System.out.println("Multiplication is: " + (a*b));
    }
}

interface A {
    void add(int a, int b);
}

interface B {
    void mul(int a, int b);
}
