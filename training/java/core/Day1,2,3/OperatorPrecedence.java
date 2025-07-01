public class OperatorPrecedence {
    public static void main(String []args) {
     
     double d1=20.00;
     double d2=80.00;
     double res=d1+d2%100;
   Boolean b1=true;
if(res!=0.00)
{
   b1=false;
}
 System.out.println(b1);
if(!b1)
{
System.out.println("got some remainder");

}

     
	
}
}