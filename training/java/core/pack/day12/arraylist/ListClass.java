package day12.arraylist;

import java.util.ArrayList;
import java.util.TreeSet;

public class ListClass {

    private TreeSet<String> lis;

    ListClass() {
        lis = new TreeSet<String>();
    }
    public void add(String s) {
    	lis.add(s);
    	displayList();
    
    }
    public void remove(String s) {
    	lis.remove(s);
    	displayList();
    }
    public void displayList() {
    	for(String s:lis)
    		System.out.println(s);
    }
    
}

