package Generics;

import java.util.ArrayList;

class Box<T>{
	private T Data;
	
	public Box(T data) {
		Data=data;
	}

	public T getData() {
		return Data;
	}

}

public class GenericExample {

	public static void main(String[] args) {
		Box<String> box=new Box<String>("Hello World!");
		System.out.println(box.getData());
		
		Box<ArrayList<Integer>> box1=new Box(new ArrayList<>());
		
		box1.getData().add(10);
		box1.getData().add(20);

		System.out.println(box1.getData());
		
		
	}

}
