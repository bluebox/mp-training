package day17;

public class MathOperations {

  public int add(int a, int b) {
      return a + b;
  }

  public int subtract(int a, int b) {
      return a - b;
  }

  public boolean isPositive(int number) {
      return number > 0;
  }

  public boolean isNegative(int number) {
      return number < 0;
  }

  public String getHello() {
      return "Hello";
  }

  public int[] getArray() {
      return new int[]{1, 2, 3};
  }

  public String getHelloString() {
      return new String("Hello");
  }

  public void throwArithmeticException() {
      int result = 1 / 0;
  }
}