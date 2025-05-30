package linkedlist;

public class MyLinkedList implements NodeList{
	public Listitem root;

	@Override
	public Listitem getRoot() {
		// TODO Auto-generated method stub
		return this.root;
	}

	@Override
	public void addItem(Node item) {
		
		Listitem node = root;
		
		if(node == null)
		{
			root = item;
		}
		else {
		
			while(node.next() != null)
			{
				node = node.next();
			}
			
			node.setRightLink(item);
			
			item.setLeftLink(node);
		}
			
		System.out.println("node added");
		
	}

	@Override
	public void RemoveItem(int index) {
		// TODO Auto-generated method stub
		
		int i = 0;
		Node Listitem = root;
		while()
		
	}

	@Override
	public void traverse() {
		// TODO Auto-generated method stub
		
	}
	
	

}
