package Day_1_7_25.DataStructures;

import java.util.Objects;

public class Node extends ListItem implements Comparable<ListItem>{
  
	public Node(int value) {
		super(value);
	}

	@Override
	ListItem next() {
		return this.rightLink;
	}

	@Override
	ListItem setnext(ListItem node) {
		// TODO Auto-generated method stub
		this.rightLink=node;
		return node;
		
	}

	@Override
	ListItem previous() {
		// TODO Auto-generated method stub
		return this.leftLink;
	}

	@Override
	ListItem setprevious(ListItem node) {
		// TODO Auto-generated method stub
		this.leftLink=node;
		return node;
	}
	
	@Override
	
public int compareTo(ListItem node) {
		// TODO Auto-generated method stub
		int value=(int)this.getValue();
		int value2=(int)node.getValue();
		return (value>=value2)?(value==value2)?0:1:-1;
	}

	
	
	
	
}
