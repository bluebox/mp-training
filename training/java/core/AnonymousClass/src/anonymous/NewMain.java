package anonymous;

public class NewMain {
	Even even1=new Even() {
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

}
