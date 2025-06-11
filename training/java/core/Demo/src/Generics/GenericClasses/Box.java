package Generics.GenericClasses;

public class Box <T>{
	
	private T content;
	
	public void set(T content) {
		this.content=content;
	}
	
	public T get() {
		return content;
	}
}
