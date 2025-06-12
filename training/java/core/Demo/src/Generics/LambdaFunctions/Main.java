package Generics.LambdaFunctions;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Calculator addition = (a,b) ->(a+b);
		Calculator subtraction = (a,b) ->(a-b);

		Calculator multiplication = (a,b) ->(a*b);

		Calculator division= (a,b) ->(a/b);
		
		
		System.out.println("for 10, 5:");
		System.out.println("Addition: "+addition.operate(10,5));
		System.out.println("Subtraction: "+subtraction.operate(10,5));
		System.out.println("Multiplication: "+multiplication.operate(10,5));
		System.out.println("Divison: "+division.operate(10,5));



	}

}
