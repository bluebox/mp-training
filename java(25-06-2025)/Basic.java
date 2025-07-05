class challenge1
{
    public static void main(String args[])
    {
        double num1=20.00d;
        double num2=80.00d;
        double num3=(num1+num2)*100.00;
        double rem=num3%40.00;
        boolean res=(rem==0.00)?true:false;
        System.out.println(res);
        if(res==false)
        {
            System.out.println("Got some remainder"+rem);
        }
        
    }
}