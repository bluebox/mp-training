public class DataTypeOverflow {
    public static void main(String[] args) {
        int num=Integer.MAX_VALUE;
        System.out.println(++num);//overflow and moves to negative end -2147483648 but throws the error when literal is passed
        int num1=2147483647;
        System.out.println(2147483649L);//this without L gives an error
    }
}
