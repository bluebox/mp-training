package Debugging;

public class Main {
    public static void main(String[] args) {
        NumberArray num=new NumberArray();
        num.addNumber(3);
        num.addNumber(4);
        num.addNumber(10);
        for(int i: num.getArray()){
            System.out.println(i+" ");
        }
    }
}
