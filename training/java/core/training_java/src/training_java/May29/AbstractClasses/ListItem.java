package AbstractClasses;

public abstract class ListItem {
	protected ListItem rightLink;
	protected ListItem leftLink;
	protected Object value;
	
	public ListItem(Object value) {
		this.value=value;
	}
	abstract ListItem next();
	abstract ListItem setNext(ListItem r);
	abstract ListItem previous();
	abstract ListItem setPrevious(ListItem p);
	abstract int compareTo(ListItem l);
	public Object getValue() {
		return value;
	}
	public void setValue(Object obj) {
		value=obj;
	}
}
