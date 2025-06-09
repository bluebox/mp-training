package WrapperClass;

public class WrapperClasses {
 public static void main(String[] args) {
    Integer i1=new Integer(1);//boxing
    Integer i2=2;//autoboxing
    Integer i3=Integer.valueOf(3);//boxing
    int i4=i3.intValue();//unboxing
    String s1="4";
    int i5=Integer.parseInt(s1);//parsing returns primitive
    Integer i6=Integer.valueOf(s1);//returns wrapper class instance
 }   
}
