package abstractClassLinkedListAndBinaryTrees;

public class MyLinkedList implements NodeList {
	private ListItem root=null;
	@Override
	public ListItem getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

	@Override
	public boolean addItem(ListItem item) {
		// TODO Auto-generated method stub
		if(root==null)
		{
			root=item;
			return true;
		}
		ListItem temp=root;
		while(temp!=null)
		{
			int comparison=temp.compareTo(item);
			if(comparison<0)
			{
				if(temp.next()!=null)
					temp=temp.next();
				else
					{temp.setNext(item);
					return true;}
			}
			else if(comparison>0)
			{
				if(temp.previous()!=null)
				{
					temp.previous().setNext(item).setPrevious(temp.previous());
					item.setNext(temp).setPrevious(item);
				}
				else
				{
					item.setNext(this.root).setPrevious(item);
					this.root=item;
				}
				return true;
			}
			else
			{
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean removeItem(ListItem removeItem) {
		// TODO Auto-generated method stub
		ListItem temp=this.root;
		while(temp!=null)
		{
			int comparison=removeItem.compareTo(removeItem);
			if(comparison<0)
			{
				
			}
			else if()
		}
	}

	@Override
	public void traverse(ListItem root) {
		// TODO Auto-generated method stub
		
	}

}
