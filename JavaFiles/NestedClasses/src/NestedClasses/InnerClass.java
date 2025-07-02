package NestedClasses;

public class InnerClass {
	
	private int num;
	
	public InnerClass(int num) {
		this.num = num;
	}

	class Inner{
		
		public int getnum() {
			return num;
		}
	}
	
	public int getnum() {
		return num;
	}
	
	public static void main(String[] args) {
		
		InnerClass innerclass =new InnerClass(5);
		System.out.println("InnerCLass returns: "+innerclass.getnum());
		
		InnerClass.Inner inner = innerclass.new Inner();

		
		System.out.println("Inner class returns: "+inner.getnum());
		
		
	}

}
