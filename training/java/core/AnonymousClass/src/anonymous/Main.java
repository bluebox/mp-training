package anonymous;

public class Main {
	static Even even=new Even() {
		public void isEven(int num) {
			if(num%2==0) {
				System.out.println(num+" is Even");
			}
			else {
				System.out.println(num+" is ODD");
			}
			
		}

		public void divBy3() {
			System.out.println("Checks divisible by 3");
		}
		
		
	};
	public static void main(String[] args) {
		even.isEven(23);
		even.isEven(22);
		even.divBy3();
		
		
	}

}
