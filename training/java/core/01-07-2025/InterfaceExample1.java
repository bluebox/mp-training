interface Parent1
{
	int area(int length,int width);
}
class Child1 implements Parent1
{
	public int area(int length,int width)
	{
		return length*width;
	}
}
public class InterfaceExample1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub]
		Child1 ch=new Child1();
		System.out.println("Area of the Reactangle"+ch.area(1, 4));

	}

}
