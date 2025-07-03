package com.tulasidhar.july1.datastructwithabstract;

public interface NodeList {
	ListItem getRoot();
	boolean addItem(ListItem li);
	boolean removeItem(ListItem li);
	void traverse();
	
}
