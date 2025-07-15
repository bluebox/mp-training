package practice;

interface B
{
	public void countWords(String name);
}
public class Ex3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		B b1=(name)->{
//			int c=0;
//			
//			for(int i=0;i<name.length();i++)
//			{
//				if(name.charAt(i)==' ')
//				{
//					c++;
//				}
//			}
//			System.out.println(c);
//			
//		};
//		b1.countWords("Pradeep is a good boy");
		
		
		
		B b1=new B()
		{

			@Override
			public void countWords(String name) {
				// TODO Auto-generated method stub
				int c=0;
				
				for(int i=0;i<name.length();i++)
				{
					if(name.charAt(i)==' ')
					{
						c++;
					}
				}
				System.out.println(c+1);
				
			}
			
		};
		b1.countWords("Hello! this istarun");

	}

}
