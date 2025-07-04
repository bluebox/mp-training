import java.util.*;

class SetExample
{
	String name;
	int roolNo;
	
	public SetExample(String name,int rollNo){
		this.name=name;
		this.roolNo=rollNo;
	}

	@Override
	public String toString() {
		return "SetExample [name=" + name + ", roolNo=" + roolNo + "]";
	}
	
}
public class SetExample2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashSet<SetExample> setexample=new HashSet<>();
		setexample.add(new SetExample("tarun",1));
		setexample.add(new SetExample("Raju",2));
		setexample.add(new SetExample("Raju",2));
		for(SetExample e:setexample)
		{
			System.out.println(e);
		}
		
		

	}

}
