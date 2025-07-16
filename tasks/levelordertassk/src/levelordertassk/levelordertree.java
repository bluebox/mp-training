package levelordertassk;

import java.util.*;
public class levelordertree {
	
	
    public static void main(String[] args){
    	Tree root=new Tree(5);
    	root.left=new Tree(4); 
    	root.right=new Tree(6);
    	root.left.left=new Tree(2);
    	root.left.right=new Tree(3);
        //Tree node = null;
		System.out.println(fun(root,0));


    }
    public static List<List<Integer>> fun(Tree node,int level){
        List<List<Integer>> result=new ArrayList<>();
        if (node==null){
            return result ;
        }
        result.get(level).add(node.val);

        fun(node.left,level+1);
        fun(node.right,level+1);
		return result;


    }
    
}



