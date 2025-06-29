
public class ThisKeywordEx2 {

	int a,b;
	ThisKeywordEx2(int a,int b)
	{
		this.a=a;
		this.b=b;
		
		System.out.println("Before calling the value of a is "+this.a +" Value of B is "+this.b);
		
		add(this);
		
		System.out.println("After calling the value of a is "+this.a +" Value of B is "+this.b);	
	}
	
	
	void add (ThisKeywordEx2 o)
	{
	
		o.a+=2;
		o.b+=2;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ThisKeywordEx2 exm = new ThisKeywordEx2(1,2);
		
		

	}

}
