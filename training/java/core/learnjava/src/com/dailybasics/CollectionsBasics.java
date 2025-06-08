package com.dailybasics;

import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

public class CollectionsBasics {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// creating array list
		List<String> fruits = new ArrayList<>(Arrays.asList("Apple", "Banana"));
		System.out.println("Fruits : " + fruits);
		// Creating empty list
		List<Integer> numbers = new ArrayList<>();
		System.out.println("is empty : " + numbers.isEmpty());
		// Conversion of ArrayList to Array
		List<String> conversionTOArray = new ArrayList<>();
		conversionTOArray.addAll(fruits);
		Object[] array = conversionTOArray.toArray();
		System.out.println("name of class" + array.getClass().getName());
		// using Iterator
		List<Integer> numbersForIterator = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println("Numbers using Iterator");
		Iterator<Integer> i = numbersForIterator.iterator();
		while (i.hasNext()) {
			System.out.println(i.next());
		}
		// Removing all elements from a list
		numbers.clear();
		System.out.println("Numbers List : " + numbers);
		// Inserting at specific Index
		fruits.add(1, "Kiwi");
		System.out.println("After adding kiwi : " + fruits);
		// Retriving an elements from LinkedList
		List<Integer> linkedList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println("1st element in LinkedList : " + linkedList.get(0));
		// replacing a element in vector
		List<String> vectorList = new Vector<>(Arrays.asList("apple", "Banana"));
		vectorList.set(1, "Kiwi");
		System.out.println("Vector List : " + vectorList);
		// Sublist of a List
		List<String> fruitsSublist = new ArrayList<>();
		fruitsSublist.addAll(fruits);
		System.out.println("all fruits except First : " + fruitsSublist.subList(1, fruitsSublist.size()));
		// finding a index of elememt in a list
		System.out.println("index of kiwi in fruits : " + fruits.indexOf("Kiwi"));
		// removing kiwi using index
		System.out.println("fruits list : " + fruits.remove(1));
		// checking all elements in a list
		System.out.println("is fruits ans fruitsSublist same : " + fruits.containsAll(fruitsSublist));
		List<Integer> numbersSublist = Arrays.asList(5, 4, 3, 2, 1);
		numbers.addAll(Arrays.asList(1, 2, 3, 4, 5));
		System.out.println("numbers and reversed numbers : " + numbers.containsAll(numbersSublist));
		System.out.println(numbers + "" + numbersSublist);
		// Hash Set
		Set<Integer> hashSet = new HashSet<>(numbers);
		hashSet.add(4);
		System.out.println("HashSet : " + hashSet);
		// converting a list to Hashset
		Set<Integer> convertToSet = new HashSet<>(numbers);
		System.out.println("Converted : " + convertToSet);
		// TreeSet
		Set<Integer> treeSet = new TreeSet<>();
		System.out.println(numbersSublist);
		for (var j : numbersSublist) {
			treeSet.add(j);
		}
		System.out.println("Tree setn: " + treeSet);
		// does treeSet contains 4
		System.out.println("Does it contains 4 : " + treeSet.contains(4));
		// TreeSet removing elements
		Set<Integer> numbersToRemove = new HashSet<>(Arrays.asList(1, 2, 3));
		treeSet.removeAll(numbersToRemove);
		System.out.println("Removing elements From set : " + treeSet);
		Set<Integer> linkedHashSet = new LinkedHashSet<>();
		linkedHashSet.add(5);
		linkedHashSet.add(9);
		linkedHashSet.add(10);
		for (var k : linkedHashSet) {
			System.out.println(k);
		}
		// finding first and last elements of set
		System.out.println(treeSet);
		System.out.println(treeSet.getClass().getName());
		TreeSet<Integer> treeSetUsingTreeSet = new TreeSet<>(treeSet);
		System.out.println(
				"Q21: TreeSet first: " + treeSetUsingTreeSet.first() + ", last: " + treeSetUsingTreeSet.last());
		// Retaining all from two sets means common elements
		treeSet.retainAll(numbers);
		System.out.println("Common elements in treeSet and numbers : " + treeSet);
		// PriorityQueue
		Queue<Integer> Q = new PriorityQueue<>();
		Q.add(1);
		Q.add(5);
		Q.add(6);
		Q.add(6);
		Q.add(1);
		System.out.println("Priority Queue : " + Q);
		// Offer and poll
		Q.offer(11);
		Q.poll();
		System.out.println(Q);
		// peeking into Queue
		System.out.println("Peeking into Q :" + Q.peek());
		// Dequeue push and pop
		Deque<Integer> deQ = new ArrayDeque<>();
		deQ.push(1);
		deQ.push(100);
		System.out.println(deQ);
		System.out.println(deQ.pop());
		// adding both sides
		deQ.addFirst(1000);
		deQ.addLast(1);
		System.out.println(deQ);
		// HashMap
		Map<Integer, String> hashMap = new HashMap<>();
		hashMap.put(1, "One");
		hashMap.put(2, "Two");
		hashMap.put(3, "THree");
		hashMap.put(4, "Four");
		hashMap.put(5, "Five");
		System.out.println("HashMap : " + hashMap);
		// retruving using ket
		System.out.println("value of one : " + hashMap.get(1));
		// Iterating using EntrySet
		for (Map.Entry<Integer, String> map : hashMap.entrySet()) {
			System.out.printf("%d : %s \n".formatted(map.getKey(), map.getValue()));
		}
		// removing value of 5
		hashMap.remove(5);
		System.out.println(hashMap);
		// finding it have a key and value
		System.out.println("does it have 4  : " + hashMap.containsKey(4));
		System.out.println("dOES IT HAVE VALUE four : " + hashMap.containsValue("Four"));
		Map<Integer, Integer> treeMap = new TreeMap<>();
		treeMap.put(2, 2);
		treeMap.put(5, 5);
		treeMap.put(1, 1);
		System.out.println("Displaying all Kays : " + treeMap.keySet());
		// adding all
		Map<Integer, String> addAllHashSet = new HashMap<>();
		addAllHashSet.putAll(hashMap);
		System.out.println(addAllHashSet);
		hashMap.computeIfAbsent(5, z -> "Five");
		System.out.println(hashMap);
		Map<Integer, String> linkedHashMap = new LinkedHashMap<>();
		linkedHashMap.putAll(hashMap);
		System.out.println(linkedHashMap);
		// replacing five value to six
		linkedHashMap.replace(5, "six");
		System.out.println(linkedHashMap);
		// Collection shufle
		Collections.shuffle(numbers);
		System.out.println(numbers);
		//reversing
		Collections.reverse(numbers);
		System.out.println("Reversed : "+numbers);
		//Sorting
		Collections.sort(numbers);
		System.out.println("Sorted Numbers : "+numbers);
		//Min Value
		System.out.println("Minimum value : "+Collections.min(numbers));
		System.out.println("Maximum value : "+Collections.max(numbers));
		//BinarySearch
		System.out.println("Finding 2 using Binary Search : "+Collections.binarySearch(numbers,2));
		
	}

}
