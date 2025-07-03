package com.tulasidhar.july1.datastructwithabstract;

public class MyLinkedList implements NodeList {
	ListItem root;
	@Override
	public ListItem getRoot() {
		return root;
	}

	@Override
	public boolean addItem(ListItem li) {
		if(root == null) {
			root = li;
			return true;
		}
		
		ListItem tempNode = root;
		while(tempNode.rightLink != null) {
			tempNode = tempNode.rightLink;
		}
		
		tempNode.rightLink = li;
		li.leftLink = tempNode;
		return true;
	}

	@Override
	public boolean removeItem(ListItem li) {
		if(root==null) {
			System.out.println("No nodes in the linked list");
			return false;
		}
		ListItem tempNode = root;
		boolean found = false;
		while(tempNode.rightLink != null) {
			if(tempNode.value == li.value) {
				found = true;
				break;
			}
			tempNode = tempNode.rightLink;
		}
		
		if(found)
		{
//			System.out.println("found the matching node->" + tempNode.leftLink.value);
//			if(tempNode.rightLink != null && tempNode.leftLink != null) {
//				System.out.println("curr " + tempNode.value);
//				System.out.println("next " + tempNode.rightLink.value);
//				System.out.println("prev "+ tempNode.leftLink.value);
//			}
			tempNode.leftLink.rightLink = tempNode.rightLink;
			tempNode.rightLink.leftLink = tempNode.leftLink;
			return true;
		}
		
		return false;
	}

	@Override
	public void traverse() {
		if(root==null) {
			System.out.println("No nodes in the linked list");
			return;
		}
		
		ListItem tempNode = root;
		
		while(tempNode.rightLink != null) {
			System.out.println(tempNode.value);
			tempNode = tempNode.rightLink;
		}
		//print the last node
		System.out.println(tempNode.value);
	}
	
}
