public class Calculations{
   public void add(int a,int b,int c){
      System.out.println("Sum of a&b&c="+(a+b+c));
   }
   public void add(int a,int b){
      System.out.println("Sum of a&b="+(a+b));
   }
   public static void main(String[] args){
      Calculations c=new Calculations();
      c.add(2,3,4);
      c.add(2,3);
   }
}