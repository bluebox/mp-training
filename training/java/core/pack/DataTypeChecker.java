
public class DataTypeChecker {
	public static void main(String[] args) {
   int var1=Integer.MAX_VALUE+1;//valid
   int var2=2147483647+1; //valid ,overflow at runtime
   
   // int erroVar=2147483648 //This gives compilation error,data type of literal and identifier not matching
		   
   System.out.println(var1);
   System.out.println(var2);
   //System.out.println(errorVar);
   
   byte var3=-128;
   byte var4=Byte.MIN_VALUE/2; 
   //byte var5=var3/2; 
   
   /* when var5=-128/2 its ok because its constant and literal compile time compiler checks and 
   find its fits in byte range compiler did the implicit narrowing as it knows the value ,but 
    byte var5=var3/2;  is not ok give compilation error because compiler says i cant do the implicit 
    narrowing because i dont know result of variables and expressions only during runtime
    you will know so i dont allow,  you must do it explicitly  */ 
   
   System.out.println(var3);
   byte var5=-128/2;
   System.out.println(var5); //generates error for commented var5
   
//   int result=8;
//   result=8-5.5;
//   System.out.println(result);
   
	}
}
//also promotion rules says that arithmetic operations over short,char,byte results to int 


