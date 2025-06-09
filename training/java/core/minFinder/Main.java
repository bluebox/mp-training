package minFinder;

public class Main {
	public static void main(String[] args) {
		MinFinder minf=new MinFinder();
		int n = minf.readInteger();
		int[] a= minf.readElements(n);
		int x = minf.findMin(a);
		System.out.println(x);
	}
}
