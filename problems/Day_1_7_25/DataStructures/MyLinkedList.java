package Day_1_7_25.DataStructures;

public class MyLinkedList implements NodeList {

	
	public ListItem root;
	
	public MyLinkedList(ListItem root) {
		this.root=root;
	}
	@Override
	public ListItem getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}

	@Override
	public boolean addItem(ListItem node) {
		// TODO Auto-generated method stub
		ListItem temp=root;
		while(temp.compareTo(node)<1 && temp.compareTo(node)!=0) {
			temp=temp.next();
			if(temp.compareTo(node)==0)return false;
		}
		node.setnext(temp.next());
		temp.setnext(node);
		return true;
	}

	@Override
	public boolean removeItem(ListItem node) {
		// TODO Auto-generated method stub
		ListItem temp=root;
		boolean ret=false;
		while(true) {
			temp=temp.next();
			if(root.compareTo(node)==0) {
				root.previous().setnext(node);
				root.setnext(null);
				ret=true;
				break;
				}
		}
		return ret;
	}

	@Override
	public void traverse(ListItem root) {
		// TODO Auto-generated method stub
		ListItem node=root;
		if(root==null)System.out.println("The list is Empty");
		while(node.next()!=null) {
			System.out.println("The value of node address :"+node.getValue().toString());
			node=node.next();
		}
		
	}
	

}
