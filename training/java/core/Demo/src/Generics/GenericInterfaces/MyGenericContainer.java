package Generics.GenericInterfaces;

public class MyGenericContainer<T> implements Container<T> {
	
	private T storedItem;
	
	@Override
	public void put(T item) {
		this.storedItem=item;
	}
	
	@Override
	public T get() {
		return storedItem;
	}
	
	@Override
	public boolean isEmpty() {
		return storedItem==null;
	}
	
	

}
