package polimorphism;

class Summation {
    int addition(int... numbers) {
        System.out.println("In primitive class");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }

    int addition(Integer... numbers) {
        System.out.println("In wrapper class");
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return sum;
    }
}

public class OverLoading {
    public static void main(String[] args) {
        Summation sum = new Summation();
        System.out.println(sum.addition(1, 2, 3, 4, 5)); // uses int... method
        Integer[] arr = {1, 2, 3, 4};
        System.out.println(sum.addition(arr));          // uses Integer... method
    }
}
