public class Swap {
 static void swapcmp(int a,int b)
{
  int temp=a;
      a=b;
      b=temp;

}
    public static void main(String []args) {
     
    int a= new int[10];
    int b=new int[20];
swapcmp(a,b);
System.out.println("a = "+a+" b = "+b);    
	
}

}