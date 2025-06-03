package Generics;
class Pair<T,S>{
	private T first;
	private S second;
	public Pair(T a,S b) {
		this.first=a;
		this.second=b;
	}
	public T getFirst() {
		return this.first;
	}
	public S getSecond() {
		return this.second;
	}
	public Pair<S,T> swap(){
		return new Pair(this.second,this.first);
	}
	@Override 
	public String toString() {
		return (String)first+(String)second;
	}
}
public class GenericPairProblem {

	public static void main(String[] args) {
		Pair<Integer,String>a=new Pair<>(10,"anand");
		System.out.println(a.getFirst());
		System.out.println(a.getSecond());
		Pair<String,Integer>b=a.swap();
		System.out.println(b.getFirst());
	}

}
