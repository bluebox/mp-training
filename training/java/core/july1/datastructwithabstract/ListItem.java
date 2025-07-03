package com.tulasidhar.july1.datastructwithabstract;

public abstract class ListItem {
	protected ListItem rightLink;
	protected ListItem leftLink;
	int value;
	
	public ListItem(int value) {
		this.value = value;
	}
	
	protected abstract ListItem next();
	protected abstract ListItem setNext(ListItem listItem);
	protected abstract ListItem previous();
	protected abstract ListItem setPrevious(ListItem listItem);
	protected abstract int compareTo(ListItem listItem);
	
	int getValue() {
		return this.value;
	}
	
	void setValue(int value) {
		this.value = value;
	}
	
	
}
