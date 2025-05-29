
public class Searchtree implements NodeList{
	private ListItem root =null;
	
//	public SearchTree(ListItem root) {
//		this.root=root;
//	}
	
	@Override
	public boolean addItem(ListItem newItem) {
		if(this.root==null) {
			this.root=newItem;
			return true;
		}
		ListItem currentItem = this.root;
		while(currentItem!=null) {
			int comparision=(currentItem.compareTo(newItem));
			if(comparision<0) {
				
				
			}else if(comparision>0) {
				
				
			}else {
				System.out.println(newItem.getValue()+" is already present");
				return false;
			}
			
		}
		return false;
		
	}
	
	@Override
	public boolean removeItem(ListItem item) {
		
	}
	
	private void performRemoval(ListItem item, ListItem parent) {
		
	}
	
	
	
	@Override
	public void traverse(ListItem root) {
	
	}

}
