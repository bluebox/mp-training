
package problems;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		
		// 1. valid string problem
		
	Stack<String> stack=new Stack<>();
	
		Scanner sc=new Scanner(System.in);
				
		String input=sc.nextLine();
		
		int length=input.length();
		System.out.println(length);
		for(int i=0;i<length-1;i++) {	
			
			char c=input.charAt(i);
			System.out.println(i);
if(c=='(' || c=='{' || c=='[' || (c=='<' && input.charAt(i+1)!='/' && 
!Character.isLetterOrDigit(input.charAt(i+1))))  {
								stack.push(input.substring(i,i+1));
			}
			else if((c=='<') && Character.isLetterOrDigit(input.charAt(i+1)) && input.charAt(i+1)!='/') {
				String temp="</";
				int ind=i+1;
				while(Character.isLetterOrDigit(input.charAt(ind))){
					temp=temp+input.charAt(ind);
					ind++;
				}
				if(input.charAt(ind)=='>') {
					temp=temp+input.charAt(ind);
				}
				
				stack.push(temp);
				i=ind;
			}
			else if(!stack.isEmpty() && c==')' && stack.peek().equals("(")) {
				stack.pop();
			}else if(!stack.isEmpty() && c=='}' && stack.peek().equals("{")) {
				stack.pop();
			}else if(!stack.isEmpty() && c==']' && stack.peek().equals("[")) {
				stack.pop();
			}else if(!stack.isEmpty() && c=='>' && stack.peek().equals("<")) {
				stack.pop();
			}else if(stack.isEmpty() && (c=='>' || c=='}' || c==']' || c==')')) {
				stack.push(input.substring(i,i+1));
			}
			else{
				String temp="</";
				int ind=i+2;
				while(Character.isLetterOrDigit(input.charAt(ind))){
					temp=temp+input.charAt(ind);
					ind++;
				}
				if(input.charAt(ind)=='>') {
					temp=temp+input.charAt(ind);
				}
				i=ind;
				System.out.println(temp);
				System.out.println(stack.peek());
				if(!stack.isEmpty() && stack.peek().equals(temp)) {
					stack.pop();
				}
				System.out.println(stack.size());
			}
			
		}
		
	    if(!stack.isEmpty()) {
	    	if((input.charAt(length-1)=='>' && stack.peek().equals("<") )|| (input.charAt(length-1)=='}' && stack.peek().equals("{")) || (input.charAt(length-1)==']' && stack.peek().equals("[")) ||
	    			(input.charAt(length-1)==')' && stack.peek().equals("(")) ){
	    		stack.pop();
	    	}
	    }else if(!Character.isLetterOrDigit(input.charAt(length-2))){
	    	stack.push(input.substring(length-1));
	    }
		
		if(stack.isEmpty()) {
			System.out.println("it is a valid string");
		}else {
			System.out.println("it is not a valid string");
		}
	
		
	///////////////////////////////////////////////////////////////////////////	
        
		//2. 9x9 sudok0 problem
		
//		Scanner sc =new Scanner(System.in);
//		
//		int sudoko [][]=new int[9][9];
//		
//		for(int i=0;i<9;i++) {
//			for(int j=0;j<9;j++) {
//			sudoko[i][j]=sc.nextInt();	
//			}
//		}
//		
//		function(sudoko);
//		
//		for(int a=0;a<9;a++) {
//			for(int b=0;b<9;b++) {
//				System.out.print(sudoko[a][b]);
//				System.out.println();
//			}
//		}
//		
//	}
//	
//	public static void function(int [][] sudoko) {
//		for(int k=0;k<9;k++) {
//			for(int j=0;j<9;j++) {
//				if(sudoko[k][j]==0) {
//					for(int i=1;i<10;i++) {
//						sudoko[k][j]=i;
//						if(found(i,k,j,sudoko)) {
//							sudoko[k][j]=0;
//							return;
//						}
//						function(sudoko);
//						
//					}
//				}
//			}
//		}
//	}
//	
//	public static boolean found(int value,int row,int col,int [][] sudoko) {
//		
//		// checking if element is present in the row
//		for(int i=0;i<9;i++) {
//			if(sudoko[row][i]==value) {
//				return false;
//			}
//		}
//		
//		//checking if element is present in the column
//		for(int j=0;j<9;j++) {
//			if(sudoko[j][col]==value) {
//				return false;
//			}
//		}
//		
//		
//		// checking element presence in the 3x3 submatrix
//		int rowstart=(row/3)*3;
//		int colstart=(col/3)*3;
//		for(int r1=rowstart;r1<rowstart+3;r1++) {
//			for(int c1=colstart;c1<colstart+3;c1++) {
//				if(sudoko[r1][c1]==value) {
//					return false;
//				}
//			}
//		}
//		
//		
//		return true;
//	}

		
	////////////////////////////////////////////////	
		
	}
}
