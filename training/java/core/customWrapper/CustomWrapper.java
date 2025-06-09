package customWrapper;
import customWrapper.Maximum;
public class CustomWrapper {
	public static void main(String[] args) {
		Maximum m=new Maximum();
		m.insert(1);;
		System.out.println("Number of elements inserted : "+m.size1());
		m.insert(10);
		m.insert(3);
		System.out.println("Maximum element value : "+m.maxi());
		m.insert(24);
		System.out.println("Maximum element value : "+m.maxi());
		System.out.println("Number of elements inserted : "+m.size1());
	}
}
