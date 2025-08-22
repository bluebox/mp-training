enum Fruit{
	APPLE("This is Apple"),
	BANANA("This is banana"),
	ORANGE("This is Orange");
	
	private final String fname;
	

	private Fruit(String fname) {
		this.fname = fname;
	}


	public String getFname() {
		return fname;
	}
	
}
public class EnumConstructor {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Fruit f=Fruit.APPLE;
		System.out.println(Fruit.ORANGE);
		System.out.println(f);

	}

}
