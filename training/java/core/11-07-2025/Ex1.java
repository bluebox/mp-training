package practice;
class Ex1
{
	
	public static void main(String args[])
	{
		Pair<String,Integer> p=new Pair<>("tarun",11);
		System.out.println(p.getName()+" "+p.getId());
		Pair<Integer,String> p1=new Pair<>(1,"Hari");
		System.out.println(p1.getName()+" "+p1.getId());
	}
}

class Pair<T1,T2>
{
	private T1 name;
	private T2 id;
	public T1 getName() {
		return name;
	}
	public T2 getId() {
		return id;
	}
	public Pair(T1 name, T2 id) {
		super();
		this.name = name;
		this.id = id;
	}
	@Override
	public String toString() {
		return "Pair [name=" + name + ", id=" + id + "]";
	}
}
//Create a generic class called Pair that can hold two elements of potentially different types.
//Task:
//Define the Pair class:
//It should be a generic class with two type parameters, say T1 and T2.
//It should have two private fields, first of type T1 and second of type T2.
//It should have a constructor that takes two arguments, one for first and one for second, to initialize these fields.
//Provide public getter methods, getFirst() and getSecond(), to retrieve the values of the first and second elements respectively.
//Demonstrate its usage in a main method:
//Create an instance of Pair to store an Integer and a String.
//Create another instance of Pair to store a Double and a Boolean.
//Print the values of the elements from both Pair instances.