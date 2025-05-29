
public class MyLinkedList implements NodeList {
	private ListItem root=null;
	public MyLinkedList(ListItem root) {
		this.root=root;
	}
	
	@Override
	public ListItem getRoot() {
		return this.root;	
	}
	public boolean addItem(ListItem newItem) {
		if(this.root==null) {
			this.root=newItem;
			return true;
		}
		ListItem currentItem=this.root;
		while(currentItem != null ) {
			int comparision=(currentItem.compareTo(newItem));
			
			if(comparision<0) {
				if(currentItem.next()!=null) {
					currentItem=currentItem.next();
				}else {
					currentItem.setNext(newItem).setPrevious(currentItem);
					return true;
				}
			}else if(comparision>0) {
				if(currentItem.previous()!=null) {
					currentItem.previous().setNext(newItem).setPrevious(currentItem);
					newItem.setNext(currentItem).setPrevious(newItem);
				}else {
					newItem.setNext(this.root).setPrevious(newItem);
					this.root=newItem;
				}
				return true;
			}else {
				System.out.println(newItem.getValue()+" is already present, so not added.");
				return false;
			}
		}
		return false;
	}
	
	public boolean removeItem(ListItem item) {
		if(item!=null) {
			System.out.println("Deleting item "+item.getValue());
		}
		ListItem currentItem=this.root;
		while(currentItem!=null) {
			int comparision=currentItem.compareTo(item);
			if(comparision==0) {
				if(currentItem==this.root) {
					this.root=currentItem.next();
					
				}else {
					currentItem.previous().setNext(currentItem.next());
					if(currentItem.next()!=null) {
						currentItem.next().setPrevious(currentItem.previous());
					}
				}
				return true;
			}else if(comparision<0) {
				currentItem=currentItem.next();
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public void traverse(ListItem root) {
		if(root==null) {
			System.out.println("The List is empty");
		}else {
			while(root!=null) {
				System.out.println(root.getValue());
				root=root.next();
			}
		}
		
	}
	
}
