package annotations.project;

public class pvehicle {
	public pvehicle() {
		System.out.println("vehicle bean created by spring");
	}
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	private String name;
	@Override
	public String toString() {
		return "pvehicle [name=" + name + "]";
	}
	public void printHello() {
		System.out.println("Printing hello from component Vehicle bean");
		
	}
	

}
