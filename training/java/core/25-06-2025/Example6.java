
public class Example6 {
	public static void main(String args[])
	{
		double discount;
	    int totalBill=6000;
	    if(totalBill>5000)
	    {
	    	discount= (totalBill*20)/100;
	    	System.out.println("Total Discount Applied for your product is" + discount);
	    }
	    else if(totalBill>2000)
	    {
	    	discount=(totalBill*10)/100;
	    	System.out.println("Total Discount Applied for your product is" + discount);
	    }
	    else
	    {
	    	System.out.println("No discount Applied");
	    }
	    
	}

}
