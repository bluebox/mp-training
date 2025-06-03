package learn2;

public class AnoonymousMain {
	
	public static void main(String[] args) {
		
		AnonymousClass obj = new AnonymousClass() {

			@Override
			public void print() {
				System.out.println("The name in the interface is "+name);
			}
			
		};
		obj.print();
	}
}
