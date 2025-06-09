package Mutability;


class ClassA {
    public final void show(){
        System.out.println("ClassA show method");
    }
}
class ClassB extends ClassA {
    // public void show(){
    //     System.out.println("ClassB show method");
    // }
}
public class FinalKeyword {
    public static void main(String[] args) {
        int num1=8;
        num1=10;
        System.out.println(num1);
        final int num2=8;
        // num2=10; // This line would cause a compilation error because num2 is declared as final
        System.out.println(num2);
    }
}
