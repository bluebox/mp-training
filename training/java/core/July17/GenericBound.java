package July17.Practice;

class Bound<T extends A> {
	T obj;

	Bound(T obj) {
		this.obj = obj;
	}

	public void fun() {
		this.obj.print();
	}
}

class A {
	public void print() {
		System.out.println("Class A");
	}
}

class B extends A {
	@Override
	public void print() {
		System.out.println("Class B");
	}
}

class C extends A {
	@Override
	public void print() {
		System.out.println("Class C");
	}
}

public class GenericBound {
	public static void main(String[] args) {
		Bound<A> a = new Bound<A>(new A());
		a.fun();
		Bound<B> b = new Bound<B>(new B());
		b.fun();
		Bound<C> c = new Bound<C>(new C());
		c.fun();
//		Bound<String> s = new Bound<String>(new String()); gives error
	}
}
