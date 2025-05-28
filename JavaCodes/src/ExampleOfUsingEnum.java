
public class ExampleOfUsingEnum {

	public static void main(String[] args) {
		ExampleOfEnum a=ExampleOfEnum.MON;
		System.out.println(a.name()+" "+a.ordinal());
		// .name() converts the name to string an .ordinal gives the index of it
		ExampleWithValue b=ExampleWithValue.MAXVALUE;
		System.out.println(b.getValue());
		ExampleWithValue c=ExampleWithValue.MINVALUE;
		System.out.println(c.getValue());
		ExampleWithMethod d=ExampleWithMethod.METHOD;
		System.out.println(d.add(20, 30));
	}

}