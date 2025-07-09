
public class Innerclass {
	private int id=1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Innerclass i1=new Innerclass();
		Test1 t1=i1.new Test1();
		t1.show();
	}
	class Test1
	{
		int id=2;
		public void show()
		{
			int id=3;
			System.out.println(id);
			System.out.println(this.id);
			System.out.println(Innerclass.this.id);
		}
	}
	class Test2
	{
		Test1 t2=new Test1();
	}

}
