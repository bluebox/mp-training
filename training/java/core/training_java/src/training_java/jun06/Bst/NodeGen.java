package Bst;

public class NodeGen<T> {
	T value;
	NodeGen<T> right,left;
	public NodeGen(T value){
		this.value=value;
		right=left=null;
	}
	

}