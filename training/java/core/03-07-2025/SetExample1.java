import java.util.*;
public class SetExample1 {

	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		// TODO Auto-generated method stub
		HashSet<Integer> h=new HashSet<>();
		h.add(10);
		h.add(20);
		h.add(30);
		System.out.println(h);
	int num=s.nextInt();
	if(h.contains(num))
		  System.out.println("Found");
	else
		  System.out.println("not found");
	}
}
