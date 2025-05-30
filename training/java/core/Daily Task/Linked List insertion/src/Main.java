package com.example;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{

	public static void main(String[] args) {
		ArrayList<Integer> a=new ArrayList<>();
		Scanner sc=new Scanner(System.in);
		int n=sc.nextInt();
		for(int i=0;i<n;i++) {
			a.add(sc.nextInt());
		}
		LinkedList l = new LinkedList(0);
		LinkedList p = l;
		for(int i:a) {
			if((i%2)==0) {
				l.add(i, l);
				l=l.next;
			}
		}
		for(int i:a) {
			if((i%2)!=0) {
				l.add(i, l);
				l=l.next;
			}
		}
		p=p.next;
		while(p!=null) {
			System.out.println(p.val);
			p=p.next;
		}
	}
}
