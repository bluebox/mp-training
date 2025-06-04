
public class HybridInheritance {
	public static void main(String[] args) {
		D objD = new D();
		objD.methodA();
		objD.methodC();
		objD.methodD();
		
		C objC = new C();
		objC.methodA();
		objC.methodC();
		
		B objB = new B();
		objB.methodA();
		objB.methodB();
		
		A objA = new A();
		objA.methodA();
	}
}

class A {
	void methodA() {
		System.out.println("This is A's method called from " + this.getClass().getName() + "'s object");
	}
}

class B extends A {
	void methodB() {
		System.out.println("This is B's method called from " + this.getClass().getName() + "'s object");
	}
}

class C extends A {
	void methodC() {
		System.out.println("This is C's method called from " + this.getClass().getName() + "'s object");
	}
}

class D extends C {
	void methodD() {
		System.out.println("This is D's method called from " + this.getClass().getName() + "'s object");
	}
}