
public class Main {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setAge(21);
		p1.setFirstName("Akash");
		p1.setLastName("Madhunala");
		System.out.println(p1.getAge());
		System.out.println(p1.getFulName());
		Person p2 = new Person();
		p2.setFirstName("Abc");
		System.out.println(p2.getFulName());
		System.out.println(p1.isTeen());
		

	}

}
