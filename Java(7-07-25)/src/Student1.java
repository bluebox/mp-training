import java.util.ArrayList;

public class Student1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MethodReference mf=new MethodReference();
		ArrayList<String>as=new ArrayList<>();
		as.add("Sreeja");
		as.add("Chandana");
		as.add("Teja");
	as.forEach(mf::print);
	}
}
