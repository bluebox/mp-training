package GenericsPractise;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Practise_2 {
	public static void main(String[] args) {
		GenericPair<String, Double> genericPair = new GenericPair<String, Double>();
		genericPair.addItem("Agent", 47.00);
		System.out.println("First element of pair: " + genericPair.getFirst());
		System.out.println("Second element of pair: " + genericPair.getSecond());

		GenericMap<String, Double> genericMap = new GenericMap<String, Double>();
		genericMap.addKeyValue("I'm Vengeance", 6.00);
		genericMap.addKeyValue("I've spoken", 1.6);
		genericMap.addKeyValue("I am here", 78.9);
		genericMap.addKeyValue("Detroit Smash", 101.1);

		System.out.println("Key: " + "I'm Vengeance" + " ,Value: " + genericMap.getValueFromKey("I'm Vengeance"));
		System.out.println("Key: " + "I've spoken" + " ,Value: " + genericMap.getValueFromKey("I've spoken"));

		GenericList<String> genericList = new GenericList<String>();
		genericList.addListItem("Ludhiana");
		genericList.addListItem("Indraprastha");
		genericList.addListItem("Pune");
		genericList.addListItem("Hyderabad");
		genericList.addListItem("Bangaluru");
		
		System.out.println("List element at index->[2]:" + genericList.getListItem(2) );
	}
}

class GenericPair<K, V> {
	private K k;
	private V v;

	public void addItem(K k, V v) {
		this.k = k;
		this.v = v;
	}

	public K getFirst() {
		return k;
	}

	public V getSecond() {
		return v;
	}
}

class GenericList<E> {
	private List<E> list = new ArrayList<E>();

	public void addListItem(E e) {
		list.add(e);
	}

	public E getListItem(int i) {
		return list.get(i);
	}
}

class GenericMap<K, V> {
	private Map<K, V> map = new HashMap<K, V>();

	public void addKeyValue(K k, V v) {
		map.put(k, v);
	}

	public V getValueFromKey(K k) {
		return map.get(k);
	}
}