package Day_1_7_25.DataStructures;



public abstract class ListItem {
     protected ListItem rightLink;
     protected ListItem leftLink;
     protected Object value;
     
	public ListItem getRightLink() {
		return rightLink;
	}
	public void setRightLink(ListItem rightLink) {
		this.rightLink = rightLink;
	}
	public ListItem getLeftLink() {
		return leftLink;
	}
	public void setLeftLink(ListItem leftLink) {
		this.leftLink = leftLink;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
     
	public ListItem(int value) {
		this.value=value;
	}
     
 abstract ListItem next();
 abstract ListItem setnext(ListItem node);
 abstract ListItem previous();
 abstract ListItem setprevious(ListItem node);   
 abstract int compareTo (ListItem node);   
     
}
