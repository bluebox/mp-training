package Package1;

public class Daily_Problem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Tree treeObj=new Tree();
		treeObj.addNode(100);
		treeObj.addNode(20);
		treeObj.addNode(30);
		treeObj.addNode(200);
		treeObj.addNode(150);
		treeObj.addNode(300);
		treeObj.addNode(10);

		treeObj.Printinorder();
		treeObj.Printpostorder();
		treeObj.Printpreorder();

		

	}

}
