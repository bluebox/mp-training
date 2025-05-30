package linkedlist;

public abstract class Listitem{
	
	private Listitem rightLink;
	private Listitem leftLink;
	private int value;
	
	public Listitem(Listitem rightLink, Listitem leftLink, int value) {
		super();
		this.rightLink = rightLink;
		this.leftLink = leftLink;
		this.value = value;
	}
	
	
	
	public Listitem getRightLink() {
		return rightLink;
	}



	public void setRightLink(Listitem rightLink) {
		this.rightLink = rightLink;
	}



	public Listitem getLeftLink() {
		return leftLink;
	}



	public void setLeftLink(Listitem leftLink) {
		this.leftLink = leftLink;
	}



	public int getValue() {
		return value;
	}



	public void setValue(int value) {
		this.value = value;
	}



	public abstract Listitem next();
	public abstract void setNext(Listitem items);
	public abstract Listitem previous();
	public abstract void setPrevious(Listitem items);
	public abstract int compareTo(Listitem l1);
	
}
