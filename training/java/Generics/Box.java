package Generics;

public class Box <T> {
	T[] dryFruits;
	int idx;
	public Box(int size) {
		dryFruits = (T[]) new Object[size];
		index=0;
	}
	public void add(Object dryFruit) {
		dryFruits[idx++]=dryFruit;
	}
	public Object get(int i) {
		return dryFruits[i];
	}
}
