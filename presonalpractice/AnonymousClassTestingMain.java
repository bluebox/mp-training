package dev.tulasidhar.presonalpractice;

public class AnonymousClassTestingMain {
	public static void main(String[] args) {
		
		BikeForAnon ns200 = new BikeForAnon();
		
		ns200.accelerate();
		
		// Now creating a enfield bike which will have new behaviour 
		// defined in the anon class syntax
		
		
		//Now this variable kind of has the obj of child class of BikeForAnon
		// (Not exactly but helps to understand the concept)
		
		BikeForAnon classic350 = new BikeForAnon() {
			@Override
			public void accelerate() {
				System.out.println("This bike's engine is a long stroke , it accelerates slow but has high torque");
			}
		};
		
		classic350.accelerate();
				
	}
}
