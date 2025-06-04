package anonymous;

public class Main {
	static Even even=new Even() {
		@Override
		public void isEven(int num) {
			// TODO Auto-generated method stub
			if(num%2==0) {
				System.out.println(num+" is Even");
			}
			else {
				System.out.println(num+" is ODD");
			}
			
		}

		@Override
		public void divBy3() {
			// TODO Auto-generated method stub
			System.out.println("Checks divisible by 3");
			
		}
		
		
	};
	public static void main(String[] args) {
		even.isEven(23);
		even.isEven(22);
		even.divBy3();
		NewMain obj=new NewMain();
		obj.even1.isEven(2);
		
		
	}

}
