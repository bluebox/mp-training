public class TypeCasting {
    public static void main(String[] args) {
        int num1=100;
        double num2=num1;
        System.out.println(num2);//implicit type casting like smaller data types to bigger data types.
        int num3=(int)num2;
        System.out.println(num3);//explicit type casting bigger data types into smaller data types.
        String s1=String.valueOf(num1);//converting to string
        System.out.println(s1);
        int num4=Integer.parseInt(s1);//string to different data type for this instance we use Integer.parseInt().
        System.out.println(num4);
    }
}
