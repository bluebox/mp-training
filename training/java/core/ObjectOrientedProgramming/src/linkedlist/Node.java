package linkedlist;

public class Node extends Listitem{

	public Node(Listitem rightLink, Listitem leftLink, int value) {
		super(rightLink, leftLink, value);
		
	}

	

	@Override
	public Listitem next() {
		return super.getRightLink();
		
	}

	@Override
	public void setNext(Listitem item) {
		super.setRightLink(item);
		
	}

	@Override
	public Listitem previous() {
		return super.getRightLink();
		
	}

	@Override
	public void setPrevious(Listitem item) {
		
		super.setLeftLink(item);
	}
	
 
	@Override
	public int compareTo(Listitem o) {
		// TODO Auto-generated method stub
		return this.getValue() - o.getValue();
	}
	

}
