package polimorphism;


class Arthemetic
{
	Integer function(int a, int b)
	{
		return a+b;
	}
}

public class MethodOverRiding {
	
	public static void main(String[] args) {
		Arthemetic sub = new Arthemetic() {
			@Override
			Integer function(int a, int b)//anonymous inner class
			{
				return a>b?a-b:b-a;
			}
		};
		
		System.out.println(sub.function(10, 29));
		
		
	}
}
