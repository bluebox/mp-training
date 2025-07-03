package com.tulasidhar.july1.datastructwithabstract;

public class Node extends ListItem{

	public Node(int value) {
		super(value);
	}

	@Override
	protected ListItem next() {
		return this.rightLink;
	}

	@Override
	protected ListItem setNext(ListItem listItem) {
		this.rightLink = listItem;
		return this.rightLink;
	}

	@Override
	protected ListItem previous() {
		return this.leftLink;
	}

	@Override
	protected ListItem setPrevious(ListItem listItem) {
		this.leftLink = listItem;
		return this.leftLink;
	}

	@Override
	protected int compareTo(ListItem listItem) {
		if(this.value == listItem.value) {
			return 0;
		}
		if(this.value < listItem.value) {
			return -1;
		}
		if(this.value > listItem.value) {
			return 1;
		}
		return 0;
	}
	
}
