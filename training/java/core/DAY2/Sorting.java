package training.java.core.DAY2;

import java.util.*;
public class Sorting{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        Random rnd=new Random();       
        int n=8;        
        int a[]= new int[n];
        for(int i=0;i<n;i++){
            a[i]=rnd.nextInt(50);
        } 
        printArray(a);
        sortArrayDesc(a);
        printArray(a);
    }
    public static void printArray(int arr[]){
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
    public static void sortArrayDesc(int a[]){
        for(int i=0;i<a.length-1;i++){
            for(int j=0;j<a.length-i-1;j++){
                if(a[j]<a[j+1]){
                    int temp= a[j];
                    a[j]=a[j+1];
                    a[j+1]=temp;
                }
            }
        }
    }
}