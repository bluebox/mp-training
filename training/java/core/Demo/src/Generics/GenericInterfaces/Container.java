package Generics.GenericInterfaces;

public interface Container <T>{
	
	void put(T item);
	T get();
	boolean isEmpty();

}
