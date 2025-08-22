
public class Main {

	public static void main(String[] args) {
		Person p1=new Person();
		p1.setFirstName("");
		p1.setSecondName("");
		p1.setAge(15);
		System.out.println("fullname  :"+p1.getFullName());
		System.out.println("is teen= "+p1.isTeen());
		Person p2=new Person();
		p2.setFirstName("charan");
		p2.setAge(18);
		System.out.println("fulname :"+p2.getFullName());
		System.out.println("is teen= "+p2.isTeen());
		Person p3=new Person();
		p3.setSecondName("kumar");
		p3.setAge(20);
		System.out.println("fulname :"+p3.getFullName());
		System.out.println("is teen="+p3.isTeen());
		Person p4=new Person();
		p4.setFirstName("charan");
		p4.setSecondName("kumar");
		p4.setAge(23);
		System.out.println("fulname :"+p4.getFullName());
		System.out.println("is teen="+p4.isTeen());


	}

}
