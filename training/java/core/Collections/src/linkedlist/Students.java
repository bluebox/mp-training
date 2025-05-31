package linkedlist;

public class Students implements Comparable<Students> {
	int id;
	String name;
	public Students(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Students [id=" + id + ", name=" + name + "]";
	}
	public int compareTo(Students a)
	{
		
		return this.name.compareTo(a.name);
	}

}
